import React from "react";
import { Modal } from "../../components/Modal/Modal";

import "./Home.css";
import "../../styles/global.css";
import { RegisterModal } from "../../features/register/components/RegisterModal/RegisterModal";

export const Home: React.FC = () => {
  return (
    <div className="main-container">
      <RegisterModal />
    </div>
  );
};
