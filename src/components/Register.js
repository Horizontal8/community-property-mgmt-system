import React, {Component} from 'react';
import { Form, Input, Icon, Button, Checkbox } from 'antd';

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
                {/*username */}
                <Form.Item label="Username">
                    {
                        getFieldDecorator('username', {
                            rules: [
                                { required: true, message: 'Please input your username!' }
                            ]
                        })(<Input />)
                    }
                </Form.Item>
                {/*phone num*/}
                <Form.Item
                    name="phone"
                    label="Phone Number"
                    rules={[
                        {
                            required: true,
                            message: 'Please input your phone number!',
                        },
                    ]}
                >
                    <Input
                        style={{
                            width: '100%',
                        }}
                    />
                </Form.Item>
                {/*e-mail*/}
                <Form.Item
                    name="email"
                    label="Email"
                    rules={[
                        {
                            required: true,
                            message: 'Please input your email address!',
                        },
                    ]}
                >
                    <Input
                        style={{
                            width: '100%',
                        }}
                    />
                </Form.Item>
                {/*password*/}
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
                {/*re-enter password*/}
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
                {/*agreement*/}
                <Form.Item
                    name="agreement"
                    valuePropName="checked"
                    rules={[
                        {
                            validator: (_, value) =>
                                value ? Promise.resolve() : Promise.reject(new Error('Should accept agreement')),
                        },
                    ]}
                    {...tailFormItemLayout}
                >
                    {/*put in the agreement href*/}
                    <Checkbox>
                        I have read the <a href="">agreement</a>
                    </Checkbox>
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
                    body: JSON.stringify({
                        username: values.username,
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
