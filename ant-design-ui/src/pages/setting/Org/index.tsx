import { addUser, removeUser, updateUser } from '@/services/ant-design-pro/api';
import { PlusOutlined } from '@ant-design/icons';
import ProForm, {
  ModalForm,
  ProFormText,
  ProFormTextArea,
  ProFormTreeSelect,
} from '@ant-design/pro-form';
import { PageContainer } from '@ant-design/pro-layout';
import type { ActionType } from '@ant-design/pro-table';
import ProTable from '@ant-design/pro-table';
import { Button, message, Popconfirm } from 'antd';
import { useRef, useState } from 'react';
import { FormattedMessage } from 'umi';

type OrgItem = {
  id: number;
  orgName: string;
  orgCode: string;
  description: string;
  parentId: number;
};

function Org() {
  const actionRef = useRef<ActionType>();
  const [createModalVisible, handleModalVisible] = useState<boolean>(false);
  const [updateModalVisible, handleUpdateModalVisible] = useState<boolean>(false);
  const [modifyOrg, setModifyOrg] = useState<OrgItem>();

  const handleAdd = async (fields?: any) => {
    const hide = message.loading('Adding');
    try {
      hide();
      const response = await addUser({ ...fields });
      if (response.message == 'OK') {
        message.success('Added successfully');
      }
      return true;
    } catch (error) {
      hide();
      return false;
    }
  };

  const handleUpdate = async (fields: any) => {
    const hide = message.loading('Modifying');
    try {
      //修改
      hide();
      const response = await updateUser({ ...fields });
      if (response.message == 'OK') {
        message.success('Modify successfully');
        actionRef.current?.reloadAndRest?.();
      }
      return true;
    } catch (error) {
      hide();
      message.error('Modify failed, please try again!');
      return false;
    }
  };

  const handleRemove = async (id: number) => {
    const hide = message.loading('Deleting');
    try {
      // 删除
      hide();
      const response = await removeUser(id);
      if (response.message == 'OK') {
        message.success('Deleted successfully');
        actionRef.current?.reloadAndRest?.();
      }
      return true;
    } catch (error) {
      hide();
      message.error('Delete failed, please try again');
      return false;
    }
  };

  const columns = [
    {
      title: 'OrgName',
      dataIndex: 'orgName',
      key: 'orgName',
    },
    {
      title: 'OrgCode',
      dataIndex: 'orgCode',
      key: 'orgCode',
      width: '12%',
    },
    {
      title: 'Description',
      dataIndex: 'description',
      key: 'description',
    },
    {
      title: 'Option',
      valueType: 'option',
      key: 'option',
      width: '10%',
      render: (_, record?: any) => [
        <a
          key="editable"
          onClick={() => {
            handleUpdateModalVisible(true);
            setModifyOrg(record);
          }}
        >
          Edit
        </a>,
        <Popconfirm
          key="delete"
          title="Are you sure to delete this org?"
          onConfirm={() => {
            handleRemove(record.id);
          }}
          okText="Yes"
          cancelText="No"
        >
          <a href="#">Delete</a>
        </Popconfirm>,
      ],
    },
  ];

  return (
    <PageContainer
      header={{
        breadcrumb: {},
      }}
    >
      <ProTable
        headerTitle={'Org List'}
        cardBordered
        actionRef={actionRef}
        rowKey={(record) => record.id}
        columns={columns}
        search={false}
        pagination={{ showSizeChanger: true, pageSize: 10 }}
        toolBarRender={() => [
          <Button
            type="primary"
            key="primary"
            onClick={() => {
              handleModalVisible(true);
            }}
          >
            <PlusOutlined /> <FormattedMessage id="pages.searchTable.new" defaultMessage="New" />
          </Button>,
        ]}
      />

      <ModalForm
        title="New Org"
        layout="vertical"
        modalProps={{ destroyOnClose: true }}
        visible={createModalVisible}
        onVisibleChange={handleModalVisible}
        onFinish={async (value) => {
          const success = await handleAdd(value);
          if (success) {
            handleModalVisible(false);
            if (actionRef.current) {
              actionRef.current.reload();
            }
          }
        }}
      >
        <ProForm.Group>
          <ProFormText
            rules={[
              {
                required: true,
                message: 'Org Name is required',
              },
            ]}
            width="md"
            name="orgName"
            label="OrgName"
          />
          <ProFormText
            rules={[
              {
                required: true,
                message: 'Org Code is required',
              },
            ]}
            width="md"
            name="orgCode"
            label="OrgCode"
          />
        </ProForm.Group>
        <ProFormTreeSelect
          name="parentId"
          label="Parent Org"
          placeholder="Please select"
          allowClear
          width={330}
          secondary
          rules={[
            {
              required: true,
              message: 'Parent Org is required',
            },
          ]}
          request={async () => {
            return [
              {
                title: 'Node1',
                value: '0-0',
                children: [
                  {
                    title: 'Child Node1',
                    value: '0-0-0',
                  },
                ],
              },
              {
                title: 'Node2',
                value: '0-1',
                children: [
                  {
                    title: 'Child Node3',
                    value: '0-1-0',
                  },
                  {
                    title: 'Child Node4',
                    value: '0-1-1',
                  },
                  {
                    title: 'Child Node5',
                    value: '0-1-2',
                  },
                ],
              },
            ];
          }}
          // tree-select args
          fieldProps={{
            treeLine: true,
            showArrow: false,
            filterTreeNode: true,
            showSearch: true,
            dropdownMatchSelectWidth: false,
            labelInValue: true,
            autoClearSearchValue: true,
            treeNodeFilterProp: 'title',
            fieldNames: {
              label: 'title',
            },
          }}
        />
        <ProFormTextArea name="description" label="Description" />
      </ModalForm>

      <ModalForm
        title="Modify Org"
        layout="vertical"
        modalProps={{ destroyOnClose: true }}
        visible={updateModalVisible}
        onVisibleChange={handleUpdateModalVisible}
        onFinish={async (value) => {
          const success = await handleUpdate(value);
          if (success) {
            handleUpdateModalVisible(false);
            if (actionRef.current) {
              actionRef.current.reload();
            }
          }
        }}
      >
        <ProFormText initialValue={modifyOrg?.id} name="id" hidden />
        <ProFormText
          rules={[
            {
              required: true,
              message: 'Org Name is required',
            },
          ]}
          initialValue={modifyOrg?.orgName}
          width="md"
          name="orgName"
          label="OrgName"
        />
        <ProFormText
          rules={[
            {
              required: true,
              message: 'Org Code is required',
            },
          ]}
          initialValue={modifyOrg?.orgCode}
          width="md"
          name="orgCode"
          label="OrgCode"
        />

        <ProFormTextArea
          name="description"
          label="Description"
          initialValue={modifyOrg?.description}
        />
      </ModalForm>
    </PageContainer>
  );
}

export default Org;
