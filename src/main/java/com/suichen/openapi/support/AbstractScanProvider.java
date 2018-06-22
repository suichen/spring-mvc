package com.suichen.openapi.support;

import org.springframework.core.type.classreading.MetadataReader;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractScanProvider extends ScanProvider{
    public abstract boolean filter(MetadataReader metadataReader);
    public abstract String getBasePackage(String basePackage);

    public List<MetadataReader> doScan(String basePackage) {
        return ScanProvider.getInstance().doScan(getBasePackage(basePackage)).stream()
                .filter(this::filter).collect(Collectors.toList());
    }
}
