import { StyledInputProps } from "./GlobalInterfaces";

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
