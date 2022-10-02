import { StyledInputProps, ValidatedInputState } from "./GlobalInterfaces";

export const styledInputBorder = (props: StyledInputProps): string => {
  let { active, valid, theme } = props;

  if (!active && valid) {
    return `1px solid ${theme.colors.lightGray}`;
  }
  if (!active && !valid) {
    return `1px solid ${theme.colors.error}`;
  }
  if (active && valid) {
    return `2px solid ${theme.colors.success}`;
  }
  if (active && !valid) {
    return `2px solid ${theme.colors.error}`;
  }
  return "";
};

export const labelColor = (props: StyledInputProps): string => {
  let { active, valid, theme, color } = props;

  if (color && color === "error") {
    return theme.colors.error;
  }
  if (color && color === "success") {
    return theme.colors.success;
  }

  return theme.colors.lightGray;
};

export const determineValidatedStyles = (
  state: ValidatedInputState,
  validator: (value: string) => boolean
): ValidatedInputState => {
  let { valid, active, typedIn, value, labelColor, labelActive } = state;

  if (typedIn) {
    valid = validator(value);
    if (active && valid) {
      labelActive = true;
      labelColor = "blue";
    }
    if (active && !valid) {
      labelActive = true;
      labelColor = "error";
    }
    if (!active && valid) {
      labelActive = true;
      labelColor = "gray";
    }

    if (!active && !valid) {
      labelActive = false;
      labelColor = "gray";
    }
  } else {
    if (active) {
      labelActive = true;
      labelColor = "blue";
    } else {
      labelActive = false;
      labelColor = "gray";
    }
  }

  state = {
    ...state,
    valid,
    labelActive,
    labelColor,
  };

  return state;
};
