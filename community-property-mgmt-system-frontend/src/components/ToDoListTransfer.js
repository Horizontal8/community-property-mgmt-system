import React from "react";
import "antd/dist/antd.css";
import { Transfer, Table, Tag, } from "antd";
import difference from "lodash/difference";

// Customize Table Transfer
const TableTransfer = ({ leftColumns, rightColumns, ...restProps }) => (
    <Transfer {...restProps} showSelectAll={false}>
        {({
              direction,
              filteredItems,
              onItemSelectAll,
              onItemSelect,
              selectedKeys: listSelectedKeys,
              disabled: listDisabled
          }) => {
            const columns = direction === "left" ? leftColumns : rightColumns;

            const rowSelection = {
                getCheckboxProps: (item) => ({
                    disabled: listDisabled || item.disabled
                }),
                onSelectAll(selected, selectedRows) {
                    const treeSelectedKeys = selectedRows
                        .filter((item) => !item.disabled)
                        .map(({ key }) => key);
                    const diffKeys = selected
                        ? difference(treeSelectedKeys, listSelectedKeys)
                        : difference(listSelectedKeys, treeSelectedKeys);
                    onItemSelectAll(diffKeys, selected);
                },
                onSelect({ key }, selected) {
                    onItemSelect(key, selected);
                },
                selectedRowKeys: listSelectedKeys
            };

            return (
                <Table
                    rowSelection={rowSelection}
                    columns={columns}
                    dataSource={filteredItems}
                    size="small"
                    style={{ pointerEvents: listDisabled ? "none" : null }}
                    onRow={({ key, disabled: itemDisabled }) => ({
                        onClick: () => {
                            if (itemDisabled || listDisabled) return;
                            onItemSelect(key, !listSelectedKeys.includes(key));
                        }
                    })}
                />
            );
        }}
    </Transfer>
);
// MockDate for test below
const mockTags = ["Low", "Medium", "High"];
const mockKeys = ["ToDo", "Done"];
const mockData = [];
for (let i = 0; i < 20; i++) {
    mockData.push({
        key: i.toString(),
        date: `2021-04-${i + 1}`,
        detail: `description of detail${i + 1}`,
        disabled: false,
        priority: mockTags[i % 3],
        sign: mockKeys[i % 2],
    });
}
console.log(mockData); //test

const originTargetKeys = mockData
    .filter((item) => item.sign === "ToDo")
    .map((item) => item.key);
// MockDate for test above
const leftTableColumns = [
    {
        dataIndex: "date",
        title: "Date"
    },
    {
        dataIndex: "priority",
        title: "Priority",
        render: (priority) => <Tag>{priority}</Tag>
    },
    {
        dataIndex: "detail",
        title: "Detail"
    }
];
const rightTableColumns = [
    {
        dataIndex: "date",
        title: "Date"
    },
    {
        dataIndex: "priority",
        title: "Priority",
        render: (tag) => <Tag>{tag}</Tag>
    },
    {
        dataIndex: "detail",
        title: "Detail"
    }
];

class ToDoListTransfer extends React.Component {
    state = {
        targetKeys: originTargetKeys,
        showSearch: true,
        selectedKeys:[],
    };

    onChange = (nextTargetKeys) => {
        this.setState({ targetKeys: nextTargetKeys });

    };

    render() {
        const { targetKeys, disabled, showSearch} = this.state;
        return (
            <div className="to-do-list-transferTable">
                <TableTransfer
                    dataSource={mockData}
                    targetKeys={targetKeys}
                    disabled={disabled}
                    showSearch={showSearch}
                    onChange={this.onChange}
                    //operations={['Done','Undo']}
                    disabled={true}
                    filterOption={(inputValue, item) =>
                        item.date.indexOf(inputValue) !== -1 ||
                        item.priority.indexOf(inputValue) !== -1||
                        item.detail.indexOf(inputValue) !== -1
                    }
                    leftColumns={leftTableColumns}
                    rightColumns={rightTableColumns}
                />
            </div>
        );
    }
}

export default ToDoListTransfer;