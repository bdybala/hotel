import React, { Component } from 'react';
import './ManageUsers.css';

import { Button, Modal } from 'react-bootstrap';

import UsersList from './UsersList';
import RegisterUser from './RegisterUser';

import axios from 'axios';

const API = 'http://192.168.0.227:8080/api';
const FIND_USERS = '/users';
const DELETE_USERS = '/users';

class ManageUsers extends Component {
	constructor(props) {
		super(props);
		this.closeErrorModal = this.closeErrorModal.bind(this);
		this.showErrorModal = this.showErrorModal.bind(this);
		this.refreshUsersList = this.refreshUsersList.bind(this);
		this.removeUser = this.removeUser.bind(this);

		this.state = { 
			show: false,
			errorMessage: 'ddddd',
			users: [],
			isLoading: false,
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

	refreshUsersList() {
		this.setState({ isLoading: true })
		axios.get(API + FIND_USERS)
		.then(result => this.setState({ users: result.data.users, isLoading: false }))
		.catch(error => {
			this.setState({ isLoading: false });
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

	render() {
		return (
			<div className='ManageUsers'>
				<RegisterUser showErrorModal={this.showErrorModal} fields={this.state.fields} refreshUsersList={this.refreshUsersList} />
				<UsersList users={this.state.users} isLoading={this.state.isLoading} deleteUser={this.removeUser} />
			
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
	}
}

export default ManageUsers;