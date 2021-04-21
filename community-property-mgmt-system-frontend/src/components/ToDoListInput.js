import React, {Component} from 'react';
import 'antd/dist/antd.css';
import {Form, Button, Input, Icon, DatePicker, Select} from 'antd';

class ToDoListInputForm extends Component {
    handleSubmit = e =>{
        e.preventDefault();
        this.props.form.validateFields((err, fieldsValue) =>{
            if(err){
                //console.log('Received values of form:', values);
                return;
            }

        const values = {
            ...fieldsValue,
            'date-picker': fieldsValue['date-picker'].format('YYYY-MM-DD'),
            'detail':fieldsValue['detail'],
        };
        console.log('Received values of form :', values);
        });
    };


    render() {
        const { getFieldDecorator } = this.props.form;

        const config = {
            rules: [{ type: 'object', required: true, message: 'Please select date!' }],
        };
        return (
            <Form onSubmit={this.handleSubmit} className="todo-list-input-form">
                <Form.Item>
                    {getFieldDecorator('date-picker',config)(<DatePicker className="date-picker"/>)}
                </Form.Item>
                <Form.Item>{getFieldDecorator('priority',{
                    rules: [{required: true, message:'Please chose your priority !'}],
                })(
                    <Select placeholder="Choose the priority" style={{width:'50%'}}>
                        <Select.Option value="Low">Low</Select.Option>
                        <Select.Option value="Medium">Mid</Select.Option>
                        <Select.Option value="High">High</Select.Option>
                    </Select>)}
                </Form.Item>
                <Form.Item>
                    {getFieldDecorator('detail', {
                        rules: [{ required: true, message: 'Please input your to-do-list !' }],
                    })(
                        <Input
                            prefix={<Icon type="audit" style={{ color: 'rgba(0,0,0,.25)' }} />}
                            type="detail"
                            placeholder="To-Do-List"
                            className="todo-list-input"
                        />
                    )}
                </Form.Item>
                <Form.Item>
                    <Button type="primary" htmlType="submit" className="login-form-button">
                        Submit
                    </Button>
                </Form.Item>
            </Form>
        );
    }
}

const ToDoListInput = Form.create({name:"todo-list"})(ToDoListInputForm);
export default ToDoListInput;

