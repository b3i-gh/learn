import modalImputStyles from './ModalInput.module.css'
import { useState } from 'react';

function ModalInput(props) {
    if (props.isKMModalOpen | props.isEuroModalOpen) {
        return (
            <div className={modalImputStyles.modal} id="modal">
                <div className={modalImputStyles.modalContent}>
                    <span className={modalImputStyles.close} onClick={(e) => {props.hideModal();}}>&times;</span>
                    <form onSubmit={props.handleSubmit}>
                        <label>
                            Enter a number ({props.isKMModalOpen ? "KM" : "€"}):
                            <input
                                type="number"
                                step="1"
                            />
                        </label>
                        <button type="submit">Submit</button>
                    </form>
                </div>
            </div>
        )
    } else
        return (<></>)
}

export default ModalInput