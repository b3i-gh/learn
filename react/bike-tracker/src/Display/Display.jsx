import displayStyles from './Display.module.css'
import ModalInput from '../ModalInput/ModalInput.jsx'
import React, { useState } from 'react';

function Display(props) {

    const getInitialKMValue = () => {
        const lsCurrKM = parseFloat(localStorage.getItem('currKM'));
        return lsCurrKM !== null ? lsCurrKM : 1;
    };

    const getInitialEuroValue = () => {
        const lsCurrEuro = parseFloat(localStorage.getItem('currEuro'));
        return lsCurrEuro !== null ? lsCurrEuro : 1;
    };

    const [savedKM, setSavedKM] = useState(getInitialKMValue);
    const [savedEuro, setSavedEuro] = useState(getInitialEuroValue);

    let currKM = savedKM;
    let currEuro = savedEuro;

    const [currRatio, setCurrRatio] = useState((currEuro/currKM).toFixed(2));
    const [isKMModalOpen, setIsKMModalOpen] = useState(false);
    const [isEuroModalOpen, setIsEuroModalOpen] = useState(false);

    let inputValue = 0;

    const updateRatio = () => {
        let ratio = (currEuro / currKM).toFixed(2);
        setCurrRatio(ratio);
    }

    const hideModal = () => {
        setIsKMModalOpen(false);
        setIsEuroModalOpen(false);         
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        inputValue = parseFloat(e.target[0].value);
        if (!isNaN(inputValue)) {
            if(isKMModalOpen){
                currKM = savedKM + inputValue;
                setSavedKM(currKM);
                localStorage.setItem('currKM', currKM);
            } else {
                currEuro = savedEuro + inputValue;
                setSavedEuro(currEuro);
                localStorage.setItem('currEuro', currEuro);
            }                
            updateRatio();
            hideModal();
        }
        e.target[0].value = 0;
    };

    return (
        <div className={displayStyles.kmDisplay}>
            <div onClick={() => {setIsKMModalOpen(true); }} className={displayStyles.editableValue}>{currKM} KM</div>
            <div onClick={() => {setIsEuroModalOpen(true); }} className={displayStyles.editableValue}>{currEuro} €</div>
            <div className={displayStyles.kmDisplayLine}>{currRatio} €/KM</div>
            <ModalInput
                isKMModalOpen={isKMModalOpen} 
                isEuroModalOpen={isEuroModalOpen}
                hideModal={hideModal}                
                handleSubmit={handleSubmit}
            />
        </div>
    )


}

export default Display