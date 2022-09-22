import React, { useState, useEffect } from "react";
import { TextInput } from "../../../../../components/TextInput/TextInput";
import { ValidatedInput } from "../../../../../components/ValidatedInput/ValidatedInput";

import "./RegFormOne.css";

interface FormOneState {
  firstName: string;
  lastName: string;
  email: string;
  dateOfBirth: string;
}

export const RegFormOne: React.FC = () => {
  const [stepOne, setStepOne] = useState<FormOneState>({
    firstName: "",
    lastName: "",
    email: "",
    dateOfBirth: "",
  });

  const updateUser = (e: React.ChangeEvent<HTMLInputElement>): void => {
    setStepOne({ ...stepOne, [e.target.name]: e.target.value });
  };

  useEffect(() => {
    console.log("State change: ", stepOne);
  }, [stepOne]);

  return (
    <div className="reg-step-one-container">
      <div className="reg-step-one-content">
        <ValidatedInput
          name={"firstName"}
          label={"First"}
          errorMessage={"Whats your name?"}
          changeValue={updateUser}
          validator={() => true}
        />
        <ValidatedInput
          name={"lastName"}
          label={"Last"}
          errorMessage={"Whats your name?"}
          changeValue={updateUser}
          validator={() => true}
        />
        <ValidatedInput
          name={"email"}
          label={"Email"}
          errorMessage={"Please enter a valid email"}
          changeValue={updateUser}
          validator={() => true}
        />
      </div>
    </div>
  );
};
