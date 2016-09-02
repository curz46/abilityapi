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

import com.github.abilityapi.listener.AbilityListener;
import com.github.abilityapi.trigger.Trigger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Ability {

    protected long startMillis = System.currentTimeMillis();
    private List<AbilityListener> listeners = new CopyOnWriteArrayList<>();

    public abstract long getExpireTicks();

    public abstract void start();

    public abstract void update();

    public abstract void stop();

    public boolean hasExpired() {
        return System.currentTimeMillis() > startMillis + getExpireTicks() * 50;
    }

    public AbilityListener addListener(Trigger trigger) {
        AbilityListener listener = new AbilityListener(this, trigger);
        listeners.add(listener);

        return listener;
    }

    public List<AbilityListener> getListeners() {
        return listeners;
    }

}