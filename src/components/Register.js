import React, {Component} from 'react';
import { Form, Input, Icon, Button } from 'antd';

import { Link } from "react-router-dom";
import { API_ROOT } from '../constants';


class RegistrationForm extends Component {
    state = {
        confirmDirty: false
    };

    render() {
        const { getFieldDecorator } = this.props.form;

        const formItemLayout = {
            labelCol: {
                xs: { span: 24 },
                sm: { span: 8 },
            },
            wrapperCol: {
                xs: { span: 24 },
                sm: { span: 16 },
            },
        };
        const tailFormItemLayout = {
            wrapperCol: {
                xs: {
                    span: 24,
                    offset: 0,
                },
                sm: {
                    span: 16,
                    offset: 8,
                },
            },
        };

        return (
            <Form {...formItemLayout} onSubmit={this.handleSubmit}
                  className="register">
                <Form.Item label="Email">
                    {
                        getFieldDecorator('username', {
                            rules: [
                                { required: true, message: 'Please input your username!' }
                            ]
                        })(<Input />)
                    }
                </Form.Item>
                <Form.Item
                    name="email"
                    label="Username"
                    tooltip="What do you want others to connect you?"
                    rules={[
                        {
                            whitespace: true,
                        },
                    ]}
                >
                    <Input />
                </Form.Item>
                <Form.Item
                    name="phoneNum"
                    label="Phone Number"
                    rules={[
                        {
                            whitespace: true,
                        },
                    ]}
                >
                    <Input />
                </Form.Item>
                <Form.Item label="Password" hasFeedback>
                    {
                        getFieldDecorator('password', {
                            rules: [
                                {
                                    required: true,
                                    message: 'Please input your password!',
                                },
                                {
                                    validator: this.validateToNextPassword,
                                }
                            ]
                        })(<Input.Password />)
                    }
                </Form.Item>
                <Form.Item label="Confirm Password" hasFeedback>
                    {
                        getFieldDecorator('confirm', {
                            rules: [
                                {
                                    required: true,
                                    message: 'Please confirm your password!',
                                },
                                {
                                    validator: this.compareToFirstPassword,
                                }
                            ]
                        })(<Input.Password onBlur={this.handleConfirmBlur} />)
                    }
                </Form.Item>

                <Form.Item {...tailFormItemLayout}>
                    <Button type="primary" htmlType="submit">
                        Register
                    </Button>

                    <p>I already have an account, go back to <Link to="/login">login</Link></p>
                </Form.Item>
            </Form>
        );
    }

    validateToNextPassword = (rule, value, callback) => {
        const { form } = this.props;
        if (value && this.state.confirmDirty) {
            form.validateFields(['confirm'], { force: true });
        }
        callback();
    };

    compareToFirstPassword = (rule, value, callback) => {
        const { form } = this.props;
        if (value && value !== form.getFieldValue('password')) {
            callback('Two passwords that you enter is inconsistent!');
        } else {
            callback();
        }
    };

    handleConfirmBlur = e => {
        const { value } = e.target;
        this.setState({ confirmDirty: this.state.confirmDirty || !!value });
    };

    handleSubmit = e => {
        e.preventDefault();
        this.props.form.validateFieldsAndScroll((err, values) => {

            if (!err) {
                console.log('Received values of form: ', values);

                fetch(`${API_ROOT}/signup`, {
                    method: 'POST',
                    headers:{
                        'Content-Type':'application/json'
                    },

                    body: JSON.stringify({
                        email: values.username,
                        password: values.password
                    })
                })
                    .then(response => {
                        console.log(response);
                        if(response.ok) {
                            return response.text();
                        }
                    })
                    .then(data => {
                        console.log(data)
                        this.props.history.push('/login');
                    })
            }
        });
    };
}

const Register = Form.create({ name: 'register' })(RegistrationForm);

export default Register;
