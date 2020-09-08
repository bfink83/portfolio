function zzzOnClick() {
    let output = document.querySelector(".output");
    if (output.style.display === "block") {
        output.style.display = "none";
    } else {
        output.style.display = "block";
    }
    let hours = "";

    //get current time
    let now = Date.now();
    let minute = 60 * 1000;
    let cycle = now;
    //allow 14 min to fall asleep
    cycle += 14 * minute;

    // calculate six sleep cycles
    for (let i = 0; i < 6; i++) {
        cycle += 90 * minute;
        hours += new Date(cycle).toLocaleTimeString([], {hour: '2,digit', minute: '2-digit'});
        if (i < 5) {
            hours += " or ";
        }
    }
    

    //append the hours with the formatted sleep cycles
    

    let hoursElm = document.querySelector(".hours");
    hoursElm.innerText = hours;
}