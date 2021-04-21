import React, {Component} from 'react';
import Calendar from "./Calendar";

import antd from "antd";
import { Tabs } from 'antd';
import { CalendarOutlined, UnorderedListOutlined, ShareAltOutlined } from '@ant-design/icons';
import Discussion from "./Discussion";
import ToDoListMain from "./ToDoListMain";
const { TabPane } = Tabs;


class Home extends Component {
    render() {
        return (
            <div>

                <Tabs defaultActiveKey="2">
                    <TabPane
                        tab={
                            <span>
          <ShareAltOutlined />
          Discussion
        </span>
                        }
                        key="1"
                    >
                        <Discussion/>
                    </TabPane>




                    <TabPane
                        tab={
                            <span>
          <CalendarOutlined />
          Calendar
        </span>
                        }
                        key="2"
                    >
                        <Calendar/>
                    </TabPane>

                    <TabPane
                        tab={
                            <span>
          <UnorderedListOutlined />
          To Do List
        </span>
                        }
                        key="3"
                    >
                        <ToDoListMain/>
                    </TabPane>



                </Tabs>,
            </div>
        );
    }
}

export default Home;
