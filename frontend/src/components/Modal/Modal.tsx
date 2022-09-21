import React from "react";

import "./Modal.css";
import "../../styles/global.css";

interface ModalProps {
  children: JSX.Element;
}

export const Modal: React.FC<ModalProps> = (props: ModalProps) => {
  return (
    <div className="overlay ">
      <div className="home-container bgWhite">{props.children}</div>
    </div>
  );
};
