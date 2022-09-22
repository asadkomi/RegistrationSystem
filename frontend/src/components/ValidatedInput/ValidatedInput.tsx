import React, { useState, useEffect } from "react";

import { StyledInputBox, StyledInputLabel } from "./StyledInput";

import "./ValidatedInput.css";

interface ValidatedUserInputProps {
  name: string;
  label: string;
  errorMessage: string;
  validator(value: string): boolean;
  changeValue(e: React.ChangeEvent<HTMLInputElement>): void;
  attribute?: Record<string, string | number | boolean>;
}

export const ValidatedInput: React.FC<ValidatedUserInputProps> = ({
  name,
  label,
  errorMessage,
  validator,
  changeValue,
  attribute,
}) => {
  return (
    <div className="validated-input">
      <StyledInputBox active={false} valid={true}>
        <StyledInputLabel color={"gray"} active={false} valid={true}>
          {label}
        </StyledInputLabel>
        <input
          className="validated-input-value"
          onFocus={() => {}}
          onBlur={() => {}}
          onChange={() => {}}
          {...attribute}
        />
      </StyledInputBox>
      <span>{errorMessage}</span>
    </div>
  );
};
