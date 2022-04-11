import { commonQueryList, commonRemove } from '@/services/ant-design-pro/api';
import { PlusOutlined } from '@ant-design/icons';
import type { ActionType, ProColumns } from '@ant-design/pro-table';
import ProTable from '@ant-design/pro-table';
import { Popconfirm, Button, message } from 'antd';
import { useEffect, useImperativeHandle, useRef, useState } from 'react';
import { FormattedMessage } from 'umi';

const handleRemoveRoleUser = async (id: number) => {
  const hide = message.loading('Deleting');
  try {
    await commonRemove(`/api/sys_role_user/delete/${id}`);
    hide();
    message.success('Deleted successfully');
    return true;
  } catch (error) {
    hide();
    message.error('Delete failed, please try again');
    return false;
  }
};

function UserList(props: any) {
  const userActionRef = useRef<ActionType>();
  const [dataSource, setDataSource] = useState([]);
  const [totalDataSource, setTotalDataSource] = useState([]);
  const { roleId, setAddUserRoleModalVisible, userChildRef } = props;

  const fetchData = async () => {
    const result = await commonQueryList(`/api/sys_role_user/list/${roleId}`);
    if (result.success) {
      setTotalDataSource(result.data);
      setDataSource(result.data);
    }
  };

  useImperativeHandle(userChildRef, () => ({
    fetchData,
  }));

  const userColumns: ProColumns[] = [
    {
      title: 'Username',
      dataIndex: 'username',
    },
    {
      title: 'Name',
      dataIndex: 'name',
    },
    {
      title: 'Option',
      key: 'option',
      width: 80,
      valueType: 'option',
      render: (text, record) => [
        <Popconfirm
          key="delete"
          title="Are you sure to delete?"
          onConfirm={async () => {
            const success = await handleRemoveRoleUser(record.id);
            if (success) {
              if (userActionRef.current) {
                userActionRef.current.reloadAndRest();
              }
              fetchData();
            }
          }}
          okText="Yes"
          cancelText="No"
        >
          <a href="#">Delete</a>
        </Popconfirm>,
      ],
    },
  ];

  useEffect(() => {
    if (roleId) {
      fetchData();
    }
  }, [roleId]);

  return (
    <ProTable
      columns={userColumns}
      dataSource={dataSource}
      pagination={{
        pageSize: 20,
        showSizeChanger: false,
      }}
      actionRef={userActionRef}
      rowKey="id"
      toolbar={{
        search: {
          onSearch: (value) => {
            const data = totalDataSource.filter((record) => {
              const name = record.name;
              const username = record.username;
              const valueLower = value.toLocaleLowerCase();
              if (
                name.toLocaleLowerCase().includes(valueLower) ||
                username.toLocaleLowerCase().includes(valueLower)
              ) {
                return true;
              }
              return false;
            });
            setDataSource(data);
          },
        },
        actions: [
          <Button
            disabled={roleId == null ? true : false}
            type="primary"
            key="primary"
            onClick={() => {
              setAddUserRoleModalVisible(true);
            }}
          >
            <PlusOutlined /> <FormattedMessage id="pages.searchTable.new" defaultMessage="New" />
          </Button>,
        ],
      }}
      options={false}
      search={false}
    />
  );
}

export default UserList;
