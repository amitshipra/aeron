/*
 * Copyright 2016 Real Logic Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.real_logic.aeron.agent;

import uk.co.real_logic.aeron.driver.NetworkPublication;
import uk.co.real_logic.aeron.driver.PublicationImage;
import uk.co.real_logic.aeron.driver.SubscriptionLink;
import uk.co.real_logic.aeron.driver.media.ReceiveChannelEndpoint;

public class CleanupInterceptor
{
    public static class DriverConductorInterceptor
    {
        public static void cleanupImageInterceptor(final PublicationImage image)
        {
            EventLogger.LOGGER.logImageRemoval(
                image.channelUriString(), image.sessionId(), image.streamId(), image.correlationId());
        }

        public static void cleanupPublication(final NetworkPublication publication)
        {
            EventLogger.LOGGER.logPublicationRemoval(
                publication.sendChannelEndpoint().originalUriString(), publication.sessionId(), publication.streamId());
        }

        public static void cleanupSubscriptionLink(final SubscriptionLink subscriptionLink)
        {
            final ReceiveChannelEndpoint channelEndpoint = subscriptionLink.channelEndpoint();

            if (null != channelEndpoint)
            {
                final int streamId = subscriptionLink.streamId();

                EventLogger.LOGGER.logSubscriptionRemoval(
                    channelEndpoint.originalUriString(), subscriptionLink.streamId(), subscriptionLink.registrationId());
            }
        }
    }
}
