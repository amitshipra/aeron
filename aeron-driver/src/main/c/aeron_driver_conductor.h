/*
 * Copyright 2014 - 2017 Real Logic Ltd.
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

#ifndef AERON_AERON_DRIVER_CONDUCTOR_H
#define AERON_AERON_DRIVER_CONDUCTOR_H

#include "aeron_driver_context.h"
#include "concurrent/aeron_mpsc_rb.h"
#include "concurrent/aeron_broadcast_transmitter.h"
#include "concurrent/aeron_distinct_error_log.h"
#include "concurrent/aeron_counters_manager.h"

typedef struct aeron_driver_conductor_stct
{
    aeron_driver_context_t *context;
    aeron_mpsc_rb_t to_driver_commands;
    aeron_broadcast_transmitter_t to_clients;
    aeron_distinct_error_log_t error_log;
    aeron_counters_manager_t counters_manager;
}
aeron_driver_conductor_t;

int aeron_driver_conductor_init(aeron_driver_conductor_t *conductor, aeron_driver_context_t *ctx);

void aeron_driver_conductor_on_command(int32_t msg_type_id, const void *message, size_t length, void *clientd);

int aeron_driver_conductor_do_work(void *clientd);
void aeron_driver_conductor_on_close(void *clientd);

#endif //AERON_AERON_DRIVER_CONDUCTOR_H