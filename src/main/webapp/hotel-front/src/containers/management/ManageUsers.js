import React, { Component } from 'react';

import { Button, Modal } from 'react-bootstrap';

import UsersList from './UsersList';
import UserRegisterPanel from './UserRegisterPanel';
import UserSearchPanel from './UserSearchPanel';

import axios from 'axios';

const API = 'http://localhost:8080/api';
const FIND_USERS = '/users/search';
const DELETE_USERS = '/users';
const REGISTER_USER = '/users/register';
const EDIT_USER = '/users';
const FIND_ROLES = '/roles';

class ManageUsers extends Component {
	constructor(props) {
		super(props);
		this.closeErrorModal = this.closeErrorModal.bind(this);
		this.showErrorModal = this.showErrorModal.bind(this);
		this.refreshUsersList = this.refreshUsersList.bind(this);
		this.getRoles = this.getRoles.bind(this);
		this.removeUser = this.removeUser.bind(this);
		this.registerUser = this.registerUser.bind(this);
		this.editUser = this.editUser.bind(this);
		this.changePage = this.changePage.bind(this);

		this.updateSearchFields = this.updateSearchFields.bind(this);

		this.state = {
			show: false,
			errorMessage: 'ddddd',
			users: [],
			roles: [],
			isLoadingUsers: false,
			isLoadingRoles: false,
			currentPage: null,
			totalPages: null,
			searchFields: null,
		}
	}
  	closeErrorModal() {
		this.setState({ show: false });
	}
	showErrorModal(errorMessage) {
		this.setState({
			show: true,
			errorMessage: errorMessage,
		});
	}
	changePage(page) {
		this.setState({ isLoadingUsers: true });
		axios.post(API + FIND_USERS, {
			currentPage: page,
			searchFields: this.state.searchFields,
		})
		.then(result => {
			this.setState({
				currentPage: result.data.currentPage,
				totalPages: result.data.totalPages,
				users: result.data.users,
				isLoadingUsers: false });
			})
		.catch(error => {
			this.setState({ isLoadingUsers: false });
			this.showErrorModal(error.message);
		})
	}

	getRoles() {
		this.setState({ isLoadingRoles: true });
		axios.get(API + FIND_ROLES)
		.then(response => this.setState({ roles: response.data, isLoadingRoles: false }))
		.catch(error => {
			this.setState({ isLoadingRoles: false });
			this.showErrorModal(error.message);
		})
	}

	refreshUsersList() {
		this.setState({ isLoadingUsers: true });
		axios.post(API + FIND_USERS, {
				currentPage: this.state.currentPage,
				searchFields: this.state.searchFields,
		})
		.then(result => {
			this.setState({
				currentPage: result.data.currentPage,
				totalPages: result.data.totalPages,
				users: result.data.users,
				isLoadingUsers: false });
			})
		.catch(error => {
			this.setState({ isLoadingUsers: false });
			this.showErrorModal(error.message);
		})
	}

	updateSearchFields(obj) {
		this.setState({ searchFields: obj });
		this.setState({ isLoadingUsers: true })
		axios.post(API + FIND_USERS, {
				currentPage: this.state.currentPage,
				searchFields: obj,
		})
		.then(result => {
			this.setState({
				currentPage: result.data.currentPage,
				totalPages: result.data.totalPages,
				users: result.data.users,
				isLoadingUsers: false });
			})
		.catch(error => {
			this.setState({ isLoadingUsers: false });
			this.showErrorModal(error.message);
		})
	}

	removeUser(id) {
		axios.delete(API + DELETE_USERS + '/' + id)
			.then(response => {
				this.refreshUsersList();
			})
			.catch(error => {
				console.log(error);
			})
	}

	registerUser(newUser) {
		axios.post(API + REGISTER_USER, {
				firstName: newUser.firstName,
				lastName: newUser.lastName,
				email: newUser.email,
				password: newUser.firstPassword,
				roleNameEnum: newUser.roleNameEnum,
			})
			.then(result => {
				this.refreshUsersList();
			})
			.catch(error => {
				if (error.response.status === 409) {
					this.showErrorModal('Użytkownik o takim e-mail istnieje już w systemie!');
				} else {
					this.showErrorModal(error.response.data.exception);
				}
			});
	}

	editUser(editedUser) {
		axios.put(API + EDIT_USER, {
			id: editedUser.id,
            firstName: editedUser.firstName,
            lastName: editedUser.lastName,
            email: editedUser.email,
            roleNameEnum: editedUser.roleName,
		})
        .then(result => {
            this.refreshUsersList();
        })
        .catch(error => {
            if (error.response.status === 409) {
                this.showErrorModal('Użytkownik o takim e-mail istnieje już w systemie!');
            } else {
                this.showErrorModal(error.response.data.exception);
            }
        })
	}

	render() {
		return (
			<div className='ManageUsers'>
				<UserRegisterPanel
					registerUser={this.registerUser}
					roles={this.state.roles} />

				<UserSearchPanel
					showErrorModal={this.showErrorModal}
					updateSearchFields={this.updateSearchFields}
					roles={this.state.roles} />

				<UsersList
					changePage={this.changePage}
					users={this.state.users}
					currentPage={this.state.currentPage}
					totalPages={this.state.totalPages}
					isLoading={this.state.isLoadingUsers}
					deleteUser={this.removeUser}
					roles={this.state.roles}
					editUser={this.editUser}/>

				<Modal show={this.state.show} onHide={this.closeErrorModal}>
					<Modal.Header>
						<Modal.Title>Wystąpił błąd!</Modal.Title>
					</Modal.Header>
					<Modal.Body> {this.state.errorMessage} </Modal.Body>
					<Modal.Footer>
						<Button onClick={this.closeErrorModal}>Close</Button>
					</Modal.Footer>
				</Modal>
			</div>
		);
	}

	componentDidMount() {
		this.refreshUsersList();
		this.getRoles();
	}
}

export default ManageUsers;