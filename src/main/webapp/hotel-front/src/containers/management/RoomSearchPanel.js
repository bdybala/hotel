import React, { Component } from 'react';

import { Form, ControlLabel, FormControl, Glyphicon, Panel } from 'react-bootstrap';


class RoomSearchPanel extends Component {
	constructor(props) {
		super(props)
		
		this.handleSearch = this.handleSearch.bind(this);
		this.handleNumberChange = this.handleNumberChange.bind(this);
		this.handleMaxCapacityChange = this.handleMaxCapacityChange.bind(this);
		this.handleIsFreeChange = this.handleIsFreeChange.bind(this);
		this.handleRoomTypeChange = this.handleRoomTypeChange.bind(this);
		this.showErrorModal = this.showErrorModal.bind(this);

		this.state = {
			number: '',
			maxCapacity: '',
			isFree: '',
			roomTypeName: '',
		}
	}

	handleSearch() {
		let searchFields = [];
		if (this.state.number !== '') {
			searchFields.push({
				name: 'number',
				value: this.state.number,
			});
		}
		if (this.state.maxCapacity !== '') {
			searchFields.push({
				name: 'maxCapacity',
				value: this.state.maxCapacity,
			});
		}
		if (this.state.isFree !== '') {
			searchFields.push({
				name: 'isFree',
				value: this.state.isFree,
			});
		}
		if (this.state.roomTypeName !== '') {
			searchFields.push({
				name: 'roomTypeName',
				value: this.state.roomTypeName,
			});
		}
		this.props.updateSearchFields(searchFields);
	}

	handleNumberChange(e) {
		this.setState({number: e.target.value});
	}
	handleMaxCapacityChange(e) {
		this.setState({maxCapacity: e.target.value});
	}
	handleIsFreeChange(e) {
		this.setState({isFree: e.target.value});
	}
	handleRoomTypeChange(e) {
		this.setState({roomTypeName: e.target.value});
	}

	showErrorModal(message) {
		this.props.showErrorModal(message);
	}

	render() {

		var listOfRoomTypes = this.props.roomTypes.map(function(item) {
			return <option key={item.name} value={item.name}>{item.description}</option>
		});

		return (
			<div className="RoomSearchPanel">
				<Panel>
					<Panel.Heading>
					<Panel.Toggle>
						<Panel.Title className="panelTitle">
							<Glyphicon glyph="glyphicon glyphicon-collapse-down" />
							&nbsp;
							Szukanie pokojów
						</Panel.Title>
					</Panel.Toggle>
					 </Panel.Heading>
						<Panel.Body collapsible>
							<Form inline>
								<ControlLabel>Numer pokoju</ControlLabel>{' '}
								<FormControl value={this.state.number} onChange={this.handleNumberChange} type="text" placeholder="221B" />{' '}
								<ControlLabel>Pojemność</ControlLabel>{' '}
								<FormControl value={this.state.maxCapacity} onChange={this.handleMaxCapacityChange} type="number" placeholder="5" />{' '}
								<ControlLabel>Wolny</ControlLabel>{' '}
								<FormControl value={this.state.isFree} onChange={this.handleIsFreeChange} type="text" placeholder="tak" />{' '}
								<FormControl componentClass="select" placeholder="Wybierz typ pokoju" onChange={this.handleRoomTypeChange}>
									<option value='' key='null'>---</option>
									{listOfRoomTypes}
								</FormControl>{' '}
								<button type="button" className= "btn btn-primary" onClick={this.handleSearch}>
									Szukaj
								</button>
							</Form>
						</Panel.Body>
				</Panel>
			</div>
		);
	}
}

export default RoomSearchPanel;