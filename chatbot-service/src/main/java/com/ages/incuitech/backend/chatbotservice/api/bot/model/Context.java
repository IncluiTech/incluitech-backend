package com.ages.incuitech.backend.chatbotservice.api.bot.model;

import java.util.*;

import static java.util.Objects.*;

public class Context {
    private Map<String, Object> contexto;

    public Context() {
        this.contexto = new HashMap<>();
    }

    public Object get(String key) {
        return this.contexto.get(key);
    }

    public void put(String key, Object value) {
        this.contexto.put(key, value);
    }

    public void putAll(Map<String, Object> mapa) {
        this.contexto.putAll(mapa);
    }

    public Object getOrDefault(String key, Object defaultValue) {
        return this.contexto.getOrDefault(key, defaultValue);
    }

    public void remove(String key) {
        this.contexto.remove(key);
    }

    public boolean propertyIsEqualsTo(String property, Object value) {
        if (isNull(value) && isNull(this.contexto.get(property))) return true;
        if (isNull(this.contexto.get(property))) return false;

        return this.contexto.get(property).equals(value);
    }

    public boolean containsKey(String valor) {
        return this.contexto.containsKey(valor);
    }
}
