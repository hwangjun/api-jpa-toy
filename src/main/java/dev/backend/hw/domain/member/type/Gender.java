package dev.backend.hw.domain.member.type;

import java.util.Objects;

/**
 * 성별
 */
public enum Gender {
    //남자
    MAN,
    //여자
    WOMAN;

    public static Gender convertFrom(String gender) {
        if (Objects.nonNull(gender)) {
            switch (gender) {
                case "MAN":
                    return MAN;
                case "WOMAN":
                    return WOMAN;
            }
        }
        return null;
    }
}
