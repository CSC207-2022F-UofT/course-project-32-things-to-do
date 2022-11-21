package test_creation_use_case;

import entities.StudentUser;

public interface TestCreationInputBoundary {
    TestCreationResponseModel create(TestCreationRequestModel requestModel);
}
