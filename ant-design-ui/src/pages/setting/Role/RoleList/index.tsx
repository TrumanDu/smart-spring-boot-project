import { commonQueryList, commonRemove } from '@/services/ant-design-pro/api';
import { PlusOutlined } from '@ant-design/icons';
import type { ActionType, ProColumns } from '@ant-design/pro-table';
import ProTable from '@ant-design/pro-table';
import { Button, message, Popconfirm } from 'antd';
import { useImperativeHandle, useRef } from 'react';
import { FormattedMessage } from 'umi';

const handleRemove = async (id: number) => {
  const hide = message.loading('Deleting');
  try {
    await commonRemove(`/api/sys_role/delete/${id}`);
    hide();
    message.success('Deleted successfully');
    return true;
  } catch (error) {
    hide();
    message.error('Delete failed, please try again');
    return false;
  }
};

type RoleListProps = {
  onChange: (role: RoleProps) => void;
  roleChildRef: any;
  setNewRoleModalVisible: (visible: boolean) => void;
  setEditRoleModalVisible: (visible: boolean) => void;
  setRoleObj: (roleObj: RoleProps) => void;
};

function RoleList(props: RoleListProps) {
  const actionRef = useRef<ActionType>();
  const { onChange, setNewRoleModalVisible, setEditRoleModalVisible, setRoleObj, roleChildRef } =
    props;

  const roleColumns: ProColumns[] = [
    {
      title: 'RoleCode',
      key: 'roleCode',
      dataIndex: 'roleCode',
    },
    {
      title: 'RoleName',
      key: 'role',
      dataIndex: 'roleName',
    },
    {
      title: 'Option',
      key: 'option',
      width: 80,
      valueType: 'option',
      render: (text, record) => [
        <a
          key="editable"
          onClick={() => {
            setEditRoleModalVisible(true);
            setRoleObj(record);
          }}
        >
          Edit
        </a>,
        <Popconfirm
          key="delete"
          title="Are you sure to delete?"
          onConfirm={async () => {
            const success = await handleRemove(record.id);
            if (success) {
              actionRef.current?.reloadAndRest?.();
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

  const reloadList = () => {
    if (actionRef.current) {
      actionRef.current.reload();
    }
  };
  useImperativeHandle(roleChildRef, () => ({
    reloadList,
  }));
  return (
    <ProTable
      columns={roleColumns}
      request={() => commonQueryList('/api/sys_role/list')}
      actionRef={actionRef}
      rowKey="id"
      toolbar={{
        actions: [
          <Button
            type="primary"
            key="primary"
            onClick={() => {
              setNewRoleModalVisible(true);
            }}
          >
            <PlusOutlined /> <FormattedMessage id="pages.searchTable.new" defaultMessage="New" />
          </Button>,
        ],
      }}
      options={false}
      pagination={false}
      search={false}
      onRow={(record) => {
        return {
          onClick: () => {
            if (record.id) {
              onChange(record);
            }
          },
        };
      }}
    />
  );
}

export default RoleList;
