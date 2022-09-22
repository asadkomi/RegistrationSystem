interface ThemeColors {
  gray: string;
  lightGray: string;
  offWhite: string;
  white: string;
  error: string;
  success: string;
}

export interface Theme {
  colors: ThemeColors;
}

export interface StyledInputProps {
  active: boolean;
  valid: boolean;
  theme: Theme;
  color?: string;
}
