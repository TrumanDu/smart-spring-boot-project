import { useState, useRef } from 'react';
import { FooterToolbar, PageContainer } from '@ant-design/pro-layout';
import type { ActionType, ProColumns } from '@ant-design/pro-table';
import ProTable from '@ant-design/pro-table';
import { useIntl, FormattedMessage } from 'umi';
import { Button, message, Popconfirm } from 'antd';
import { PlusOutlined, QuestionCircleOutlined } from '@ant-design/icons';
import ProForm, { ModalForm, ProFormText, ProFormTextArea } from '@ant-design/pro-form';

type UserItem = {
  id: number;
  username: string;
  password: string;
  name: string;
  email: string;
  description: string;
};

type TableListPagination = {
  total: number;
  pageSize: number;
  current: number;
};

/**
 * @en-US Add node
 * @zh-CN 添加节点
 * @param fields
 */
const handleAdd = async (fields: API.RuleListItem) => {
  const hide = message.loading('正在添加');
  try {
    //
    hide();
    message.success('Added successfully');
    return true;
  } catch (error) {
    hide();
    message.error('Adding failed, please try again!');
    return false;
  }
};

/**
 * @en-US Update node
 * @zh-CN 更新节点
 *
 * @param fields
 */
const handleUpdate = async (fields: FormValueType) => {
  const hide = message.loading('Modifying');
  try {
    //修改
    hide();

    message.success('Modify successfully');
    return true;
  } catch (error) {
    hide();
    message.error('Modify failed, please try again!');
    return false;
  }
};

/**
 *  Delete node
 * @zh-CN 删除节点
 *
 * @param selectedRows
 */
const handleRemove = async (selectedRows: API.RuleListItem[]) => {
  const hide = message.loading('Deleting');
  if (!selectedRows) return true;
  try {
    // 删除
    hide();
    message.success('Deleted successfully and will refresh soon');
    return true;
  } catch (error) {
    hide();
    message.error('Delete failed, please try again');
    return false;
  }
};

const columns: ProColumns<UserItem>[] = [
  {
    dataIndex: 'index',
    valueType: 'indexBorder',
    width: 48,
  },
  {
    title: 'Username',
    dataIndex: 'username',
    copyable: true,
    ellipsis: true,
    formItemProps: {
      rules: [
        {
          required: true,
          message: '此项为必填项',
        },
      ],
    },
  },
  {
    title: 'Name',
    dataIndex: 'name',
    copyable: true,
    ellipsis: true,
    formItemProps: {
      rules: [
        {
          required: true,
          message: '此项为必填项',
        },
      ],
    },
  },
  {
    title: 'Email',
    dataIndex: 'email',
    copyable: true,
    ellipsis: true,
    formItemProps: {
      rules: [
        {
          required: true,
          message: '此项为必填项',
        },
      ],
    },
  },
  {
    title: 'Org',
    dataIndex: 'orgName',
    copyable: true,
    ellipsis: true,
    search: false,
  },
  {
    title: 'Description',
    dataIndex: 'description',
    copyable: true,
    ellipsis: true,
    search: false,
    valueType: 'textarea',
  },
];

function User() {
  const intl = useIntl();
  const actionRef = useRef<ActionType>();

  const [createModalVisible, handleModalVisible] = useState<boolean>(false);
  const [updateModalVisible, handleUpdateModalVisible] = useState<boolean>(false);

  const [showDetail, setShowDetail] = useState<boolean>(false);

  const [currentRow, setCurrentRow] = useState<API.RuleListItem>();
  const [selectedRowsState, setSelectedRows] = useState<API.RuleListItem[]>([]);

  return (
    <PageContainer
      header={{
        breadcrumb: {},
      }}
    >
      <ProTable<UserItem, TableListPagination>
        headerTitle={intl.formatMessage({
          id: 'pages.settings.user.title',
          defaultMessage: 'User List',
        })}
        search={{
          labelWidth: 120,
        }}
        cardBordered
        actionRef={actionRef}
        rowKey="key"
        columns={columns}
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
        rowSelection={{
          onChange: (_, selectedRows) => {
            setSelectedRows(selectedRows);
          },
        }}
      />

      {selectedRowsState?.length > 0 && (
        <FooterToolbar
          extra={
            <div>
              <FormattedMessage id="pages.searchTable.chosen" defaultMessage="Chosen" />{' '}
              <a style={{ fontWeight: 600 }}>{selectedRowsState.length}</a>{' '}
              <FormattedMessage id="pages.searchTable.item" defaultMessage="项" />
            </div>
          }
        >
          <Popconfirm
            title="Are you sure？"
            icon={<QuestionCircleOutlined style={{ color: 'red' }} />}
          >
            <Button
              onClick={async () => {
                await handleRemove(selectedRowsState);
                setSelectedRows([]);
                actionRef.current?.reloadAndRest?.();
              }}
            >
              <FormattedMessage
                id="pages.searchTable.batchDeletion"
                defaultMessage="Batch deletion"
              />
            </Button>
          </Popconfirm>
        </FooterToolbar>
      )}

      <ModalForm
        title={intl.formatMessage({
          id: 'pages.settings.user.add',
          defaultMessage: 'New user',
        })}
        layout="vertical"
        visible={createModalVisible}
        onVisibleChange={handleModalVisible}
        onFinish={async (value) => {
          const success = await handleAdd(value as API.RuleListItem);
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
                message: <FormattedMessage defaultMessage="User name is required" />,
              },
            ]}
            width="md"
            name="username"
            label={intl.formatMessage({
              id: 'pages.settings.user.username',
              defaultMessage: 'Username',
            })}
          />
          <ProFormText
            rules={[
              {
                required: true,
                message: <FormattedMessage defaultMessage="Password is required" />,
              },
            ]}
            width="md"
            name="password"
            label={intl.formatMessage({
              id: 'pages.settings.user.password',
              defaultMessage: 'Password',
            })}
          />
        </ProForm.Group>

        <ProForm.Group>
          <ProFormText
            rules={[
              {
                required: true,
                message: <FormattedMessage defaultMessage="Name is required" />,
              },
            ]}
            width="md"
            name="name"
            label={intl.formatMessage({
              id: 'pages.settings.user.name',
              defaultMessage: 'Name',
            })}
          />
          <ProFormText
            width="md"
            name="email"
            label={intl.formatMessage({
              id: 'pages.settings.user.email',
              defaultMessage: 'Email',
            })}
          />
        </ProForm.Group>

        <ProFormTextArea
          name="description"
          label={intl.formatMessage({
            id: 'pages.settings.user.description',
            defaultMessage: 'Description',
          })}
        />
      </ModalForm>
    </PageContainer>
  );
}

export default User;
