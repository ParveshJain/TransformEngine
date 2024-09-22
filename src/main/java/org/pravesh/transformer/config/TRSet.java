package org.pravesh.transformer.config;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class TRSet {
    @Getter
    List <TRGroup> ruleGroups;
}
