package com.mg.flashcards.utils;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BeanMapperUtil {

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    public <S,D> D map(S source, Class<D> destinationType){
        D destination = dozerBeanMapper.map(source, destinationType);
        return destination;
    }

    public <S,D> D map(S source, D destination){
        dozerBeanMapper.map(source, destination);
        return destination;
    }

    public <S,D> List<D> map(List<S> sourceList, Class<D> destinationType){
        List<D> destinationList = new ArrayList<>();
        sourceList.stream().forEach(source -> {
            D destination = dozerBeanMapper.map(source, destinationType);
            destinationList.add(destination);
        });
        return destinationList;
    }

}
