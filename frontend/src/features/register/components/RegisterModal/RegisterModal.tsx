import React, { useState } from "react";
import { Modal } from "../../../../components/Modal/Modal";
import { RegistrationSetps } from "../RegisterSteps/RegistrationSetps";

import "./RegisterModal.css";
import { modalContent } from "../../utils/RegisterModalUtils";

export const RegisterModal: React.FC = () => {
  const [step, setStep] = useState<number>(3);

  const stepBtnClicked = () => {
    step === 1 || step === 4 || step >= 6 ? setStep(step) : setStep(step - 1);
  };

  return (
    <Modal>
      <div className="register-container">
        <RegistrationSetps step={step} changeStep={stepBtnClicked} />
        <div className="register-modal">{modalContent(step)}</div>
      </div>
    </Modal>
  );
};
