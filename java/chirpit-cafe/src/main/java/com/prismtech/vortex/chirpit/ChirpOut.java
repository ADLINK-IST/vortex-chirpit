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

import com.chirpit.*;
import org.omg.dds.core.ServiceEnvironment;
import org.omg.dds.core.policy.PolicyFactory;
import org.omg.dds.domain.DomainParticipant;
import org.omg.dds.domain.DomainParticipantFactory;
import org.omg.dds.pub.DataWriter;
import org.omg.dds.pub.DataWriterQos;
import org.omg.dds.pub.Publisher;
import org.omg.dds.pub.PublisherQos;
import org.omg.dds.topic.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import static java.lang.System.out;

public class ChirpOut {
    private static final Logger logger = LoggerFactory.getLogger(Config.LOGGING_TAG_OUT);

    private final int domainId;
    private final List<String> partitions;

    private DomainParticipant dp;
    private DataWriter<ChirpAction> dw;

    public static void main(String[] args) {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        if(args.length > 0) {
            final String user = args[0];
            final ChirpOut app = new ChirpOut(Config.DEFAULT_DOMAIN_ID, Arrays.asList(args));

            app.start();
            try {
                out.println(Console.EOL + Console.EOL + Console.EOL);
                out.println(Console.GREEN + "[Press Enter to terminate]" + Console.EOL + Console.RESET);
                out.println(Console.GREEN + "chirp >> " + Console.RESET);
                String msg = reader.readLine();
                while(msg.length() > 0) {
                    app.write(createChirp(user, msg));
                    out.println(Console.GREEN + "chirp >> " + Console.RESET);
                    msg = reader.readLine();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            } finally {
                app.stop();
            }
        }
    }

    private static ChirpAction createChirp(final String user, final String msg) {
        final String uid = UUID.randomUUID().toString();
        final ChirpId id = new ChirpId(user, uid);
        final Location location = new Location();
        location.location(new SomeLocation(0,0));
        final ChirpHeader h = new ChirpHeader(id, location, System.currentTimeMillis(), ChirpActionKind.CHIRP_KIND);
        final ChirpBody b = new ChirpBody();
        b.chirp(msg);
        return new ChirpAction(h, b);
    }

    public ChirpOut(int domainId, List<String> partitions) {
        this.domainId = domainId;
        this.partitions = partitions;
    }

    public void write(final ChirpAction data) {
        try {
            dw.write(data);
        } catch (TimeoutException e) {
            logger.warn("Timeout trying to write sample.", e);
        }
    }

    public void start() {
        createDDSEntities();
    }

    public void stop() {
        dp.close();
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

        // Create a new publisher
        final PublisherQos publisherQos = dp.getDefaultPublisherQos().withPolicy(
            pf.Partition().withName(partitions)
        );

        final Publisher pub = dp.createPublisher(publisherQos);

        // Create a writer for the ChirpAction topic
        final DataWriterQos dwQos = pub.getDefaultDataWriterQos().withPolicies(
            pf.Reliability().withReliable(),
            pf.Durability().withTransientLocal()
        );
        dw = pub.createDataWriter(topic, dwQos);
    }
}
