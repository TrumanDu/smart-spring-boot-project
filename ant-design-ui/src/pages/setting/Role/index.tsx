import { PlusOutlined } from '@ant-design/icons';
import ProCard from '@ant-design/pro-card';
import ProForm, { ModalForm, ProFormText, ProFormTextArea } from '@ant-design/pro-form';
import { PageContainer } from '@ant-design/pro-layout';
import type { ProColumns } from '@ant-design/pro-table';
import ProTable from '@ant-design/pro-table';
import { Button, Popconfirm } from 'antd';
import { useState } from 'react';
import { FormattedMessage } from 'umi';

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
  /*   {
    title: 'Description',
    key: 'description',
    dataIndex: 'description',
  }, */
  {
    title: 'Option',
    key: 'option',
    width: 80,
    valueType: 'option',
    render: (text, record) => [
      <a
        key="editable"
        onClick={() => {
          /*   handleUpdateModalVisible(true);
          setModifyUser(record); */
        }}
      >
        Edit
      </a>,
      <Popconfirm
        key="delete"
        title="Are you sure to delete this user?"
        onConfirm={async () => {
          /*  const success = await handleRemove(record.id);
          if (success) {
            actionRef.current?.reloadAndRest?.();
          } */
        }}
        okText="Yes"
        cancelText="No"
      >
        <a href="#">Delete</a>
      </Popconfirm>,
    ],
  },
];

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
    render: () => [<a key="a">Delete</a>],
  },
];

type RoleListProps = {
  roleId: any;
  onChange: (id: number) => void;
  setNewRoleModalVisible: (visible: boolean) => void;
};

function RoleList(props: RoleListProps) {
  const { onChange, roleId, setNewRoleModalVisible } = props;
  return (
    <ProTable
      columns={roleColumns}
      dataSource={[]}
      rowKey="id"
      toolbar={{
        search: {
          onSearch: (value) => {
            alert(value);
          },
        },
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
    />
  );
}

function UserList(props: any) {
  const { roleId } = props;
  return (
    <ProTable
      columns={userColumns}
      dataSource={[]}
      pagination={{
        pageSize: 20,
        showSizeChanger: false,
      }}
      rowKey="id"
      toolbar={{
        search: {
          onSearch: (value) => {
            alert(value);
          },
        },
        actions: [
          <Button
            disabled={roleId == null ? true : false}
            type="primary"
            key="primary"
            onClick={() => {
              //handleModalVisible(true);
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

type RoleProps = {
  id: number;
  roleName: string;
  roleCode: string;
  description: string;
};

function Role() {
  const [roleObj, setRoleObj] = useState<RoleProps>();
  const [newRoleModalVisible, setNewRoleModalVisible] = useState(false);
  const [editRoleModalVisible, setEditRoleModalVisible] = useState(false);
  return (
    <PageContainer
      header={{
        breadcrumb: {},
      }}
    >
      <ProCard split="vertical">
        <ProCard colSpan="384px" ghost>
          <RoleList
            onChange={(roleId) => {}}
            roleId={roleObj?.id}
            setNewRoleModalVisible={setNewRoleModalVisible}
          />
        </ProCard>
        <ProCard title={roleObj?.name}>
          <UserList roleId={roleObj?.id} />
        </ProCard>
      </ProCard>

      <ModalForm
        title="New Role"
        layout="vertical"
        modalProps={{ destroyOnClose: true }}
        visible={newRoleModalVisible}
        onVisibleChange={setNewRoleModalVisible}
        onFinish={async (value) => {
          /* const success = await handleAdd(value);
          if (success) {
            setNewRoleModalVisible(false);
            if (actionRef.current) {
              actionRef.current.reload();
            }
          } */
        }}
      >
        <ProForm.Group>
          <ProFormText
            rules={[
              {
                required: true,
                message: 'Role Name is required',
              },
            ]}
            width="md"
            name="roleName"
            label="RoleName"
          />
          <ProFormText
            rules={[
              {
                required: true,
                message: 'Role Code is required',
              },
            ]}
            width="md"
            name="roleCode"
            label="RoleCode"
          />
        </ProForm.Group>

        <ProFormTextArea name="description" label="Description" />
      </ModalForm>

      <ModalForm
        title="Modify Role"
        layout="vertical"
        modalProps={{ destroyOnClose: true }}
        visible={editRoleModalVisible}
        onVisibleChange={setEditRoleModalVisible}
        onFinish={async (value) => {
          /* const success = await handleAdd(value);
          if (success) {
            setNewRoleModalVisible(false);
            if (actionRef.current) {
              actionRef.current.reload();
            }
          } */
        }}
      >
        <ProFormText initialValue={roleObj?.id} name="id" hidden />
        <ProForm.Group>
          <ProFormText
            rules={[
              {
                required: true,
                message: 'Role Name is required',
              },
            ]}
            width="md"
            name="roleName"
            label="RoleName"
            initialValue={roleObj?.roleName}
          />
          <ProFormText
            rules={[
              {
                required: true,
                message: 'Role Code is required',
              },
            ]}
            width="md"
            name="roleCode"
            label="RoleCode"
            initialValue={roleObj?.roleCode}
          />
        </ProForm.Group>

        <ProFormTextArea name="description" label="Description" />
      </ModalForm>
    </PageContainer>
  );
}

export default Role;
