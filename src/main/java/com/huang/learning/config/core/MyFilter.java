package com.huang.learning.config.core;

import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class MyFilter implements TypeFilter {
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        boolean match=false;
        if(classMetadata.getClassName().contains("Controller")||classMetadata.getClassName().contains("SpringMVCConfig")) {
            match=true;
        }else {
            System.out.println(classMetadata.getClassName());
        }
        return match;
    }
}
