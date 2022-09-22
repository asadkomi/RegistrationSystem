import React from "react";
import { ThemeProvider, createGlobalStyle } from "styled-components";

import { Home } from "./pages/Home/Home";

import "./styles/global.css";
import { Theme } from "./utils/GlobalInterfaces";

const theme: Theme = {
  colors: {
    gray: "#657786",
    lightGray: "#AAB8C2",
    offWhite: "#E1E8ED",
    white: "#F5F8FA",
    error: "red",
    success: "green",
  },
};

const GlobalStyle = createGlobalStyle`
  *{
    font-weight: 500;
  }
`;

export const App = () => {
  return (
    <ThemeProvider theme={theme}>
      <GlobalStyle />
      <Home></Home>
    </ThemeProvider>
  );
};
