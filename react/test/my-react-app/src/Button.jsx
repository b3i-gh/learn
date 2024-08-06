
function Button(){
    let count = 0;

    const handleClick = (e) => {
        e.target.textContent = "OUCH!";
    }

    const handleDoubleClick = (e) => e.target.textContent = "Click :)";
   
    const handleClick2 = (name) => console.log(`${name} stop clicking me`);

    return (<button onClick={(e) => handleClick(e)}
    onDoubleClick={(e) => handleDoubleClick(e)}>Click :)</button>);
}

export default Button