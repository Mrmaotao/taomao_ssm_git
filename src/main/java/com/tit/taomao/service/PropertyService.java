package com.tit.taomao.service;

import java.util.List;

import com.tit.taomao.entity.Property;

public interface PropertyService {
    void add(Property p);
    void delete(int id);
    void update(Property p);
    List<Property> list(int id);
    Property get(int id);
}
