import React, {Component} from 'react';

import {ControlLabel, FormControl, FormGroup, Glyphicon, InputGroup} from 'react-bootstrap';


class RoomInputRoomType extends Component {
    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
        this.state = {
            roomTypes: [],
        }
    }

    handleChange(e) {
        this.props.save(e.target.value);
    }

    getValidationState() {
        return this.props.validate('roomTypeName');
    }

    render() {
        var listOfRoomTypes = this.props.roomTypes.map(function(item) {
            return <option key={item.name} value={item.name}>{item.description}</option>
        });

        return (
            <FormGroup controlId="formRoleInput" validationState={this.getValidationState()}>
                <ControlLabel>Wybierz typ pokoju: </ControlLabel>
                <InputGroup>
                    <InputGroup.Addon><Glyphicon glyph="glyphicon glyphicon-menu-hamburger"/></InputGroup.Addon>
                    <FormControl componentClass="select" placeholder="Wybierz typ pokoju" onChange={this.handleChange}>
                        <option value='null' key='null'>---</option>
                        {listOfRoomTypes}
                    </FormControl>
                </InputGroup>
            </FormGroup>
        );
    }
}

export default RoomInputRoomType;