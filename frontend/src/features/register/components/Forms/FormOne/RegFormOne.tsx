import React, { useState, useEffect } from "react";
import { TextInput } from "../../../../../components/TextInput/TextInput";

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
        <TextInput
          name={"firstName"}
          label={"First"}
          errorMessage={"Please enter you name"}
          onChange={updateUser}
        />
        <TextInput
          name={"lastName"}
          label={"Last"}
          errorMessage={"Please enter you Last name"}
          onChange={updateUser}
        />
        <TextInput
          name={"email"}
          label={"email"}
          errorMessage={"Please enter a valid email"}
          onChange={updateUser}
        />
      </div>
    </div>
  );
};
