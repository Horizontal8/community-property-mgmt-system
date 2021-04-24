import React, {Component} from "react";
import {Divider} from "antd";
import ToDoListInput from "./ToDoListInput";
import ToDoListTransfer from "./ToDoListTransfer";

class ToDoListMain extends Component{
    constructor() {
        super();
    }

    render() {
        return(
            <div className='todoMain'>
            <ToDoListInput></ToDoListInput>
            <Divider />
            <ToDoListTransfer></ToDoListTransfer>
            </div>
        )
    }
}

export default ToDoListMain;