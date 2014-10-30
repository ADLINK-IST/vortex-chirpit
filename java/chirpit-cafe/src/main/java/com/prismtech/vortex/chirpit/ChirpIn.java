/*
 * Copyright 2014 PrismTech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.prismtech.vortex.chirpit;

import com.chirpit.ChirpAction;
import com.chirpit.ChirpActionKind;
import org.omg.dds.core.ServiceEnvironment;
import org.omg.dds.core.event.DataAvailableEvent;
import org.omg.dds.core.policy.PolicyFactory;
import org.omg.dds.domain.DomainParticipant;
import org.omg.dds.domain.DomainParticipantFactory;
import org.omg.dds.sub.*;
import org.omg.dds.topic.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public final class ChirpIn {

    private static final Logger logger = LoggerFactory.getLogger(Config.LOGGING_TAG_IN);

    private final int domainId;
    private final String user;
    private final List<String> partitions;

    private DomainParticipant dp;
    private DataReader<ChirpAction> dr;

    public static void main(String[] args) {
        if(args.length > 0) {
            final String user = args[0];
            final ChirpIn app = new ChirpIn(Config.DEFAULT_DOMAIN_ID,
                    user, Arrays.asList(args));

            Runtime.getRuntime().addShutdownHook(new Thread(){
                @Override
                public void run() {
                    if(app != null) {
                        app.stop();
                    }
                    super.run();
                }
            });

            app.start();
            try {
                Thread.currentThread().join();
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    protected ChirpIn(final int domainId, final String user, final List<String> partitions) {
        this.domainId = domainId;
        this.user = user;
        this.partitions = partitions;
        createDDSEntities();
    }

    public void start() {
        dr.setListener(new ChirpActionListener());
    }

    public void stop() {
        if(dp != null) {
            dp.close();
        }
    }

    private void createDDSEntities() {
        // Configure DDS ServiceEnvironment class to be Vortex Cafe
        System.setProperty(ServiceEnvironment.IMPLEMENTATION_CLASS_NAME_PROPERTY,
                Config.DDS_BOOTSTRAP_CLASS);

        // Create a DDS ServiceEnvironment
        final ServiceEnvironment env =
                ServiceEnvironment.createInstance(this.getClass().getClassLoader());

        // Get the DomainParticipantFactory singleton
        final DomainParticipantFactory dpf =
                DomainParticipantFactory.getInstance(env);

        // Get the PolicyFactory singleton
        final PolicyFactory pf =
                PolicyFactory.getPolicyFactory(env);

        // Create a DomainParticipant on DomainID
        dp = dpf.createParticipant(domainId);

        // Create a topic with ChirpAction type
        Topic<ChirpAction> topic = dp.createTopic(Config.CHIRP_ACTION_TOPIC, ChirpAction.class);

        // Create a new subscriber
        final SubscriberQos subQos = dp.getDefaultSubscriberQos().withPolicy(
                pf.Partition().withName(partitions)
            );

        final Subscriber sub = dp.createSubscriber(subQos);


        // Create a reader for the ChirpAction topic
        final DataReaderQos drQos = sub.getDefaultDataReaderQos().withPolicies(
                pf.Reliability().withReliable(),
                pf.Durability().withTransientLocal()
        );
        dr = sub.createDataReader(topic, drQos);
    }

    static class ChirpActionListener
        extends BaseChirpActionListener {
        @Override
        public void onDataAvailable(DataAvailableEvent<ChirpAction> e) {
            final Sample.Iterator<ChirpAction> samples = e.getSource().take();
            samples.forEachRemaining(new Consumer<Sample<ChirpAction>>() {
                @Override
                public void accept(Sample<ChirpAction> s) {
                    if(s.getData() != null && s.getData().header.kind.value() == ChirpActionKind.CHIRP_KIND.value()) {
                        System.out.println(Console.GREEN +
                            s.getData().header.id.uid + ">> " + Console.RESET + s.getData().body.chirp() + Console.EOL);
                    }
                }
            });
        }
    }

}
