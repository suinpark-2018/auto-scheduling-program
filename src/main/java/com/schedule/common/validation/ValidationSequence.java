package com.schedule.common.validation;

import javax.validation.GroupSequence;

import static com.schedule.common.validation.ValidationGroups.*;

@GroupSequence({ NotBlankGroup.class, SizeGroup.class, PatternGroup.class })
public interface ValidationSequence {

}
