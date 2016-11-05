/*
 * The MIT License (MIT)
 * Copyright (c) 2016 Chris Martin (OmniCypher-), Dylan Curzon (curz46), Connor Hartley (connorhartley)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.abilityapi;

import com.github.abilityapi.ability.AbilityManager;
import com.github.abilityapi.ability.AbilityRegistry;
import com.github.abilityapi.metrics.MetricService;
import com.github.abilityapi.sequence.SequenceInvoker;
import com.github.abilityapi.sequence.SequenceManager;
import com.github.abilityapi.services.AbilityService;
import com.github.abilityapi.services.SequenceService;
import com.github.abilityapi.services.UserService;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class AbilityAPI extends JavaPlugin {

    private static AbilityAPI instance;

    private final AbilityManager abilityManager = new AbilityManager(this);
    private final SequenceManager sequenceManager = new SequenceManager(abilityManager);

    private final MetricService metricService = new MetricService(this);
    private final AbilityService abilityService = new AbilityService(this, abilityManager);
    private final SequenceService sequenceService = new SequenceService(this, sequenceManager);
    private final UserService userService = new UserService(this);

    private final List<Service> services = new ArrayList<>();

    public static AbilityAPI get() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        services.add(metricService);
        services.add(abilityService);
        services.add(sequenceService);
        services.add(userService);
        services.forEach(Service::start);
    }

    @Override
    public void onDisable() {
        services.forEach(Service::stop);
        instance = null;
    }

    public AbilityRegistry getAbilityRegistry() {
        return sequenceManager;
    }

    public SequenceInvoker getSequenceInvoker() {
        return sequenceManager;
    }

}
