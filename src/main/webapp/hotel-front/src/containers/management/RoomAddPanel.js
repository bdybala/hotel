import React, {Component} from 'react';

import {Form, Glyphicon, Panel} from 'react-bootstrap';
import RoomInputNumber from "./RoomInputNumber";
import RoomInputMaxCapacity from "./RoomInputMaxCapacity";
import RoomInputRoomType from "./RoomInputRoomType";

class UserRegisterPanel extends Component {

    constructor(props) {
        super(props);

        this.setRoomNumber = this.setRoomNumber.bind(this);
        this.setMaxCapacity = this.setMaxCapacity.bind(this);
        this.setRoomTypeName = this.setRoomTypeName.bind(this);

        this.handleClick = this.handleClick.bind(this);
        this.getValidationState = this.getValidationState.bind(this);
        this.containsFalse = this.containsFalse.bind(this);

        this.validationArray = {
            number: '',
            maxCapacity: '',
            roomTypeName: '',
        };

        this.state = {
            number: '',
            maxCapacity: '',
            roomTypeName: '',
        };
    }

    setRoomNumber(number) {
        this.setState({number: number})
    }

    setMaxCapacity(maxCapacity) {
        this.setState({maxCapacity: maxCapacity})
    }

    setRoomTypeName(roomTypeName) {
        this.setState({roomTypeName: roomTypeName})
    }

    getValidationState(field) {
        let result = null;
        switch (field) {
            case 'number':
                let length = this.state.number.length;
                if (length > 0) {
                    result = 'success';
                    this.validationArray.number = true;
                    break;
                }
                this.validationArray.number = false;
                result = null;
                break;
            case 'maxCapacity':
                if (this.state.maxCapacity === '') {
                    this.validationArray.maxCapacity = false;
                    result = null;
                    break;
                }
                else if (this.state.maxCapacity <= 0) {
                    this.validationArray.maxCapacity = false;
                    result = 'error';
                    break;
                } else if (this.state.maxCapacity > 0) {
                    this.validationArray.maxCapacity = true;
                    result = 'success';
                    break;
                }
                this.validationArray.maxCapacity = false;
                result = null;
                break;
            case 'roomTypeName':
                if (this.state.roomTypeName === '') {
                    this.validationArray.roomTypeName = false;
                    result = null;
                    break;
                } else if (this.state.role === 'null') {
                    this.validationArray.roomTypeName = false;
                    result = 'error';
                    break;
                }
                this.validationArray.roomTypeName = true;
                result = 'success';
                break;
            default:
                result = 'warning';
        }
        return result;
    }

    handleClick() {
        if (this.containsFalse()) {
            console.log(this.state);
            console.log(this.validationArray);
        } else {
            const newRoom = {
                number: this.state.number,
                maxCapacity: this.state.maxCapacity,
                roomTypeName: this.state.roomTypeName
            };
            this.props.createNewRoom(newRoom);
        }
    }

    containsFalse() {
        let containsFalse = false;
        for (const key in this.validationArray) {
            if (!this.validationArray[key]) {
                containsFalse = true;
            }
        }
        return containsFalse;
    }

    render() {
        return (
            <div>
                <Panel>
                    <Panel.Heading>
                        <Panel.Toggle>
                            <Panel.Title className="panelTitle">
                                <Glyphicon glyph="glyphicon glyphicon-collapse-down"/>
                                &nbsp;
                                Dodanie nowego pokoju
                            </Panel.Title>
                        </Panel.Toggle>
                    </Panel.Heading>
                    <Panel.Body collapsible>
                        <Form>
                            <RoomInputNumber save={this.setRoomNumber} validate={this.getValidationState}/>
                            <RoomInputMaxCapacity save={this.setMaxCapacity} validate={this.getValidationState}/>
                            <RoomInputRoomType roomTypes={this.props.roomTypes} save={this.setRoomTypeName}
                                               validate={this.getValidationState}/>
                            <button type="button" className="btn btn-primary" onClick={this.handleClick}>
                                Dodaj
                            </button>
                        </Form>
                    </Panel.Body>
                </Panel>
            </div>
        );
    }
}

export default UserRegisterPanel;