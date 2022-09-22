import { RegFormOne } from "../components/Forms/FormOne/RegFormOne";

export const modalContent = (step: number): JSX.Element => {
  switch (step) {
    case 1:
      return <RegFormOne />;
    case 2:
      return <span>Step 2</span>;
    case 3:
      return <span>Step 3</span>;
    case 4:
      return <span>Step 4</span>;
    case 5:
      return <span>Step 5</span>;
    case 6:
      return <span>Step 6</span>;
    default:
      return <></>;
  }
};
