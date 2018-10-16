import React, {Component} from 'react';

import {Button, ButtonToolbar, Glyphicon, Table} from 'react-bootstrap';

import CustomPagination from './CustomPagination';
import UserEdit from './UserEdit';

class UsersList extends Component {
    constructor(props) {
        super(props);
        this.handleRemoveClick = this.handleRemoveClick.bind(this);
        this.changePage = this.changePage.bind(this);

        this.state = {
            usersToEdit: []
        };
    }

    handleRemoveClick(id) {
        this.props.deleteUser(id);
    }

    changePage(page) {
        this.props.changePage(page);
    }

    render() {
        const isLoading = this.props.isLoading;

        if (isLoading) {
            return <p> Loading... </p>;
        }

        if (this.props.users) {
            var tableOfUsers = (
                <Table responsive striped bordered condensed hover>
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Imię</th>
                        <th>Nazwisko</th>
                        <th>E-mail</th>
                        <th>Rola</th>
                        <th>Opcje</th>
                    </tr>
                    </thead>

                    {this.props.users.map((item, i) => {
                        return (<tbody key={item.id}>
                            <tr>
                                <td>{i + 1}</td>
                                <td>{item.firstName}</td>
                                <td>{item.lastName}</td>
                                <td>{item.email}</td>
                                <td>{item.roleName}</td>
                                <td>
                                    <ButtonToolbar>
                                        <Button bsStyle="danger"
                                                onClick={(e) => this.handleRemoveClick(item.id, e)}>
                                            <Glyphicon glyph='glyphicon glyphicon-remove'/>
                                        </Button>
                                        <Button bsStyle="info" onClick={(e) => this.handleEditClick(item.id, e)}>
                                            <Glyphicon glyph='glyphicon glyphicon-edit'/>
                                        </Button>
                                    </ButtonToolbar>
                                </td>
                            </tr>
                            {this.state.usersToEdit.includes(item.id) ?
                                <UserEdit
                                    roles={this.props.roles}
                                    user={item}
                                    editUser={this.props.editUser}/>
                                : null}

                            </tbody>
                        )
                    })}

                </Table>
            );
        }
        return (
            <div className='UsersList'>
                <h3>Lista użytkowników</h3>
                <CustomPagination changePage={this.changePage} currentPage={this.props.currentPage}
                                  totalPages={this.props.totalPages}/>
                {tableOfUsers}
            </div>
        );
    }

    handleEditClick(id, e) {
        let newUsers = this.state.usersToEdit.slice();
        if (newUsers.includes(id)) {
            newUsers = newUsers.filter(function (ele) {
                    return ele !== id;
                }
            )
        } else {
            newUsers.push(id);
        }
        this.setState({
            usersToEdit: newUsers
        })
    }
}

export default UsersList;