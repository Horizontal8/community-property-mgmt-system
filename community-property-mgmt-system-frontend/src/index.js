import React from 'react';
import ReactDOM from 'react-dom';
import 'antd/dist/antd.css';
import './index.css';
import { Tabs } from 'antd';
import { AppleOutlined, AndroidOutlined } from '@ant-design/icons';
import './components/DiscussionPage';
import Discussion from "./components/DiscussionPage";

const { TabPane } = Tabs;

ReactDOM.render(
    <Tabs defaultActiveKey="2">
        <TabPane
            tab={
                <span>
          <AppleOutlined />
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
          <AndroidOutlined />
          Calendar
        </span>
            }
            key="2"
        >
            Tab 2
        </TabPane>


        <TabPane
            tab={
                <span>
          <AndroidOutlined />
          To do
        </span>
            }
            key="3"
        >
            Tab 2
        </TabPane>


    </Tabs>,
    document.getElementById('root'),
);