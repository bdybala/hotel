import React, { Component } from 'react';

import { Button, Modal } from 'react-bootstrap';

import RoomsList from './RoomsList.js'
import RoomSearchPanel from './RoomSearchPanel.js'

import axios from 'axios';

const API = 'http://192.168.0.227:8080/api';
const FIND_ROOMS = '/rooms/search';
const FIND_ROOM_TYPES = '/roomTypes';
const DELETE_ROOMS = '/rooms';


class ManageRooms extends Component {
		constructor(props) {
		super(props);
		this.closeErrorModal = this.closeErrorModal.bind(this);
		this.showErrorModal = this.showErrorModal.bind(this);
		this.refreshRoomsList = this.refreshRoomsList.bind(this);
		this.removeRoom = this.removeRoom.bind(this);
		this.changePage = this.changePage.bind(this);

		this.updateSearchFields = this.updateSearchFields.bind(this);

		this.state = { 
			show: false,
			errorMessage: 'ddddd',
			roomTypes: [],
			rooms: [],
			isLoadingRooms: false,
			isLoadingRoomTypes: false,
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
		this.setState({ isLoading: true, isFetching: true })
		axios.post(API + FIND_ROOMS, {
			currentPage: page,
			searchFields: this.state.searchFields,
		})
		.then(result => {
			this.setState({ 
				currentPage: result.data.currentPage,
				totalPages: result.data.totalPages,
				rooms: result.data.rooms,
				isLoadingRooms: false });
			})
		.catch(error => {
			this.setState({ isLoadingRooms: false });
			this.showErrorModal(error.message);
		})
	}

	refreshRoomsList() {
		this.setState({ isLoadingRooms: true })
		axios.post(API + FIND_ROOMS, {
				currentPage: this.state.currentPage,
				searchFields: this.state.searchFields,
		})
		.then(result => {
			this.setState({ 
				currentPage: result.data.currentPage,
				totalPages: result.data.totalPages,
				rooms: result.data.rooms,
				isLoadingRooms: false });
			})
		.catch(error => {
			this.setState({ isLoadingRooms: false });
			this.showErrorModal(error.message);
		})
	}

	updateSearchFields(obj) {
		this.setState({searchFields: obj});
		this.setState({ isLoadingRooms: true })
		axios.post(API + FIND_ROOMS, {
				currentPage: this.state.currentPage,
				searchFields: obj,
		})
		.then(result => {
			this.setState({ 
				currentPage: result.data.currentPage,
				totalPages: result.data.totalPages,
				rooms: result.data.rooms,
				isLoadingRooms: false });
			})
		.catch(error => {
			this.setState({ isLoadingRooms: false });
			this.showErrorModal(error.message);
		})
	}

	removeRoom(id) {
		axios.delete(API + DELETE_ROOMS + '/' + id)
			.then(response => {
				this.refreshRoomsList();
			})
			.catch(error => {
				console.log(error);
			})
	}

	getRoomTypes(){
		this.setState({ isLoadingRoomTypes: true });
		axios.get(API + FIND_ROOM_TYPES)
			.then(response => {
				this.setState({roomTypes: response.data, isLoadingRoomTypes: false});
			})
			.catch(error => {
				this.setState({ isLoadingRoomTypes: true });
				this.showErrorModal(error.message);
			})
	}

	render() {
		return (
			<div className='ManageRooms'>
				<RoomSearchPanel
					showErrorModal={this.showErrorModal} 
					updateSearchFields={this.updateSearchFields}
					roomTypes={this.state.roomTypes} />

				<RoomsList 
					changePage={this.changePage} 
					rooms={this.state.rooms} 
					currentPage={this.state.currentPage}
					totalPages={this.state.totalPages}
					isLoading={this.state.isLoadingRooms} 
					deleteRoom={this.removeRoom} />
			
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
		this.refreshRoomsList();
		this.getRoomTypes();
	}
}

export default ManageRooms;