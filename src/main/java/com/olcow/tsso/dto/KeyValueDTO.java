package com.olcow.tsso.dto;

public class KeyValueDTO<T,K> {

    private T key;
    private K value;

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public K getValue() {
        return value;
    }

    public void setValue(K value) {
        this.value = value;
    }

    public KeyValueDTO(T key, K value) {
        this.key = key;
        this.value = value;
    }
}
