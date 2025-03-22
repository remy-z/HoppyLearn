import React, { createContext, useState, ReactNode } from "react";
import * as types from "../types";

interface EditDeckContextType {
  inputCard: types.InputCard;
  setCard: React.Dispatch<React.SetStateAction<types.InputCard>>;
}

export const EditDeckContext = createContext<EditDeckContextType>({
  inputCard: types.createInputCard(),
  setCard: () => {},
});

interface EditDeckProviderProps {
  children: ReactNode;
}

export const EditDeckContextProvider: React.FC<EditDeckProviderProps> = ({
  children,
}) => {
  const [inputCard, setCard] = useState<types.InputCard>(
    types.createInputCard()
  );
  return (
    <EditDeckContext.Provider value={{ inputCard, setCard }}>
      {children}
    </EditDeckContext.Provider>
  );
};
