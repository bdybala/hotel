import React, {Component} from 'react';

import {Button, ButtonToolbar, Glyphicon, Table} from 'react-bootstrap';

import CustomPagination from './CustomPagination';

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
                                <th>{i + 1}</th>
                                <th>{item.firstName}</th>
                                <th>{item.lastName}</th>
                                <th>{item.email}</th>
                                <th>{item.roleName}</th>
                                <th>
                                    <ButtonToolbar>
                                        <Button bsStyle="danger"
                                                onClick={(e) => this.handleRemoveClick(item.id, e)}>
                                            <Glyphicon glyph='glyphicon glyphicon-remove'/>
                                        </Button>
                                        <Button bsStyle="info" onClick={(e) => this.handleEditClick(item.id, e)}>
                                            <Glyphicon glyph='glyphicon glyphicon-edit'/>
                                        </Button>
                                    </ButtonToolbar>
                                </th>
                            </tr>
                            {this.state.usersToEdit.includes(item.id) ?
                                <tr>
                                    <td colSpan={6}>TEST</td>
                                </tr>
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