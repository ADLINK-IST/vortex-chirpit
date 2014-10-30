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
import org.omg.dds.core.event.*;
import org.omg.dds.sub.DataReaderListener;

/**
 * An abstract {@link org.omg.dds.sub.DataReaderListener} for ChirpAction.
 * Only {@link org.omg.dds.sub.DataReaderListener#onDataAvailable(org.omg.dds.core.event.DataAvailableEvent)} is
 * left unimplemented for inheriting class.
 */
public abstract class BaseChirpActionListener
    implements DataReaderListener<ChirpAction> {
    @Override
    public void onRequestedDeadlineMissed(RequestedDeadlineMissedEvent<ChirpAction> chirpActionRequestedDeadlineMissedEvent) {
        // do nothing
    }

    @Override
    public void onRequestedIncompatibleQos(RequestedIncompatibleQosEvent<ChirpAction> chirpActionRequestedIncompatibleQosEvent) {
        // do nothing
    }

    @Override
    public void onSampleRejected(SampleRejectedEvent<ChirpAction> chirpActionSampleRejectedEvent) {
        // do nothing
    }

    @Override
    public void onLivelinessChanged(LivelinessChangedEvent<ChirpAction> chirpActionLivelinessChangedEvent) {
        // do nothing
    }

    @Override
    public void onSubscriptionMatched(SubscriptionMatchedEvent<ChirpAction> chirpActionSubscriptionMatchedEvent) {
        // do nothing
    }

    @Override
    public void onSampleLost(SampleLostEvent<ChirpAction> chirpActionSampleLostEvent) {
        // do nothing
    }
}
