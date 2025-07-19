package com.hoo.user.test.domain;

import com.github.f4b6a3.uuid.UuidCreator;
import com.hoo.user.domain.User;

public class UserTestData {

    public static BusinessUserBuilder defaultBusinessUser() {
        return new BusinessUserBuilder()
                .withUserID(new User.UserID(UuidCreator.getTimeOrderedEpoch()))
                .withConsent(true, true)
                .withNickname("leaf")
                .withEmail("test@example.com");
    }

    public static class BusinessUserBuilder {
        private User.UserID userID;
        private Boolean termsOfUseConsent;
        private Boolean personalInfoConsent;
        private String nickname;
        private String email;

        public BusinessUserBuilder withUserID(User.UserID userID) {
            this.userID = userID;
            return this;
        }

        public BusinessUserBuilder withConsent(Boolean termsOfUseConsent, Boolean personalInfoConsent) {
            this.termsOfUseConsent = termsOfUseConsent;
            this.personalInfoConsent = personalInfoConsent;
            return this;
        }

        public BusinessUserBuilder withNickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public BusinessUserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return User.createBusinessUser(userID, termsOfUseConsent, personalInfoConsent, nickname, email).newBusinessUser();
        }

    }
}
