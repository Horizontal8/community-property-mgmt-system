import * as React from 'react';
import Paper from '@material-ui/core/Paper';
import { ViewState, EditingState, GroupingState, IntegratedGrouping, IntegratedEditing } from '@devexpress/dx-react-scheduler';
import {
    Scheduler,
    WeekView,
    Toolbar,
    DateNavigator,
    Appointments,
    AppointmentTooltip,
    AppointmentForm,
    TodayButton,
    Resources,
    GroupingPanel
} from '@devexpress/dx-react-scheduler-material-ui';
import moment from "moment";
import {
    teal, blue, orange
} from '@material-ui/core/colors';
import { withStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import Person from '@material-ui/icons/Person';
import Schedule from '@material-ui/icons/Schedule';
import RoomBookingService from "../../Services/RoomBookingService";

const usaTime = date => new Date(date).toLocaleString('en-US', { timeZone: 'America/Los_Angeles' });

const style = ({ palette }) => ({
    icon: {
        color: palette.action.active,
    },
    textCenter: {
        textAlign: 'center',
    },
});

const TextEditor = (props) => {
    // eslint-disable-next-line react/destructuring-assignment
    if (props.type === 'multilineTextEditor') {
        return null;
    } if (props.placeholder === 'Title') {
        return null;
    } return <AppointmentForm.TextEditor {...props} />;
};

let resource = [{id:1, text:'Room1'},{id:2, text: 'Room2'}]

let addedTest = undefined;
let editingTest = undefined;
let changesTest = undefined;

const BasicLayout = ({ onFieldChange, appointmentData, ...restProps }) => {
    const onCustomFieldChange = (nextValue) => {
        onFieldChange({ residentId: nextValue });
    };

    const onSelectFieldChange = (nextValue) => {
        onFieldChange({ location: nextValue });
    }

    return (
        <AppointmentForm.BasicLayout
            appointmentData={appointmentData}
            onFieldChange={onFieldChange}
            {...restProps}
        >
            <AppointmentForm.Label
                text="Resident"
                type="title"
            />
            <AppointmentForm.TextEditor
                value={appointmentData.residentId}
                onValueChange={onCustomFieldChange}
                placeholder="Resident Name"
            />
            {/*<AppointmentForm.Label*/}
            {/*    text="Location"*/}
            {/*    type="title"*/}
            {/*/>*/}
            {/*<AppointmentForm.Select*/}
            {/*    value={appointmentData.location}*/}
            {/*    onValueChange={onSelectFieldChange}*/}
            {/*    availableOptions={ resource }*/}
            {/*/>*/}
        </AppointmentForm.BasicLayout>
    );
};

const BooleanEditor = props => {
    return <AppointmentForm.BooleanEditor {...props} readOnly />;
};

const locations = [
    { text: 'Room 1', id: 1, color: blue },
    { text: 'Room 2', id: 2, color: orange },
    { text: 'Room 3', id: 3, color: teal }
];

const Header = () => null;

const Content = withStyles(style, { name: 'Content' })(({
                                                            children, appointmentData, classes, ...restProps
                                                        }) => (
    <AppointmentTooltip.Content {...restProps} appointmentData={appointmentData}>
        <Grid container alignItems="center">
            <Grid item xs={2} className={classes.textCenter}>
                <Person className={classes.icon} />
            </Grid>
            <Grid item xs={10}>
                <span>{appointmentData.residentId}</span>
            </Grid>
        </Grid>
    </AppointmentTooltip.Content>
));

const appointmentContent = withStyles(style, { name: 'appointmentContent' })(({
                                                                                  children, data, classes, ...restProps
                                                                              }) => (
    <Appointments.AppointmentContent {...restProps} data={data} >
        <Grid container alignItems="center">
            <Grid item xs={2} className={classes.textCenter}>
                <Schedule className={classes.icon} />
            </Grid>
            <Grid item xs={12}>
                <span>{`Resident: ${data.residentId}`}</span>
            </Grid>
            <Grid item xs={12}>
                <span>{`${moment(data.startDate).format('HH:mm')}-${moment(data.endDate).format('HH:mm')}`}</span>
            </Grid>
        </Grid>
    </Appointments.AppointmentContent>
));

class App4 extends React.PureComponent {
    constructor(props) {
        super(props);

        const theDate = moment();

        this.state = {
            data: [],
            currentDate: theDate,
            allowDeleting: true,
            resources: [{
                fieldName: 'roomId',
                title: 'Location',
                instances: locations,
            }],
            grouping: [{
                resourceName: 'roomId',
            }],
            addedEvent: {},
            addedAppointment: {},
            appointmentChanges: {},
            editingAppointment: undefined,
            editingFormVisible: false,
        };
        this.currentDateChange = (currentDate) => {
            console.log(currentDate);
            return this.setState({ currentDate });
        };
        this.commitChanges = this.commitChanges.bind(this);
        this.changeAddedAppointment = this.changeAddedAppointment.bind(this);
        this.changeAppointmentChanges = this.changeAppointmentChanges.bind(this);
        this.changeEditingAppointment = this.changeEditingAppointment.bind(this);
        this.saveOrUpdateEvent = this.saveOrUpdateEvent.bind(this);
        this.deleteEvent = this.deleteEvent.bind(this);
        this.toggleEditingFormVisibility = this.toggleEditingFormVisibility.bind(this);
    }


    componentDidMount() {
        RoomBookingService.getEvents().then((response) => {
            const results = response.data;
            const resultArray = results.map(el => {
                return {allDay:false,
                    title:undefined,
                    startDate: usaTime(el.startTime),
                endDate: usaTime(el.endTime),
                id: parseInt(el.id), roomId: parseInt(el.commonRoom.id), residentId: parseInt(el.booker.id)
                }
            })
            console.log(resultArray.length);
            this.setState({data:resultArray})
        })
    }

    commitChanges({ added, changed, deleted }) {
        this.setState((state) => {
            let { data } = state;
            if (added) {
                const startingAddedId = data.length > 0 ? data[data.length - 1].id + 1 : 0;
                data = [...data, { id: startingAddedId, ...added }];
                console.log('roomId-> ',added.roomId);
                console.log('residentId-> ',parseInt(added.residentId));
                console.log('startTime-> ',added.startDate.toISOString());
                console.log('commonRoom-> ',{"id":added.roomId});
                let event = {startTime:added.startDate.toISOString(),
                    endTime:added.endDate.toISOString(),
                    timeCreated: new Date().toISOString(),
                    booker:{id:parseInt(added.residentId)},
                    commonRoom:{id:added.roomId}};
                console.log('event => ' + JSON.stringify(event));
                this.setState({addedEvent: event});
                console.log('savedE => ' + this.addedAppointment);
            }
            if (changed) {
                console.log('changed here!-> ',changed);
                data = data.map(appointment => (
                    changed[appointment.id] ? { ...appointment, ...changed[appointment.id] } : appointment));
            }
            if (deleted !== undefined) {
                console.log('deleted here!-> ',deleted);
                data = data.filter(appointment => appointment.id !== deleted);
            }
            return { data };
        });
    }

    changeAddedAppointment(addedAppointment) {
        this.setState({ addedAppointment });
        addedTest = addedAppointment;
    }

    changeAppointmentChanges(appointmentChanges) {
        console.log('appointmentChanges-> ',appointmentChanges);
        this.setState({ appointmentChanges });
        changesTest = appointmentChanges;
    }

    changeEditingAppointment(editingAppointment) {
        console.log('editingAppointment-> ',editingAppointment);
        this.setState({ editingAppointment });
        editingTest = editingAppointment;
    }

    deleteEvent = (e) => {
        e.preventDefault();
        console.log('ifwork addedTest->', addedTest);
        console.log('ifwork editingTest->', editingTest);
        console.log('ifwork changesTest->', changesTest);
        if(editingTest != undefined) {
            let eventId = editingTest.id;
            RoomBookingService.deleteEvent(eventId).then(res => {
                console.log(res);
            }).catch(err => {
                console.log('err msg: ', err.response.data)
                alert(err.response.data)
            })
            this.commitChanges({deleted:eventId});
            this.toggleEditingFormVisibility();
        }
    }

    saveOrUpdateEvent = (e) => {
        e.preventDefault();
        console.log('ifwork addedTest->', addedTest);
        console.log('ifwork editingTest->', editingTest);
        console.log('ifwork changesTest->', changesTest);
        if(addedTest != undefined) {
            let event = {startTime:addedTest.startDate.toISOString(),
                endTime:addedTest.endDate.toISOString(),
                timeCreated: new Date().toISOString(),
                booker:{id:parseInt(addedTest.residentId)},
                commonRoom:{id:addedTest.roomId}};
            RoomBookingService.createEvent(event).then(res => {
                console.log(res);
            })
            this.commitChanges({added:addedTest});
            this.toggleEditingFormVisibility();
        } else {
            let eventId = editingTest.id;
            let newEvent = {};
            newEvent[eventId] = changesTest;
            let oldEvent = {startTime:new Date(editingTest.startDate).toISOString(),
                endTime:new Date(editingTest.endDate).toISOString(),
                timeCreated: new Date().toISOString(),
                booker:{id:parseInt(editingTest.residentId)},
                commonRoom:{id:editingTest.roomId}};
            if(changesTest != undefined){
                for (let key in changesTest) {
                    if (key === 'startDate') {
                        oldEvent.startTime = changesTest.startDate.toISOString();
                    } else if ( key === 'endDate') {
                        oldEvent.endTime = changesTest.endDate.toISOString();
                    } else if (key === 'roomId') {
                        oldEvent.commonRoom = {id:changesTest.roomId}
                    } else if (key === 'residentId') {
                        oldEvent.booker = {id:parseInt(changesTest.residentId)}
                    }
                }
            }
            console.log('oldEvent-> ',oldEvent);
            RoomBookingService.updateEvent(oldEvent, eventId).then(res => {
                console.log('trythisone-> ',oldEvent);
                //console.log(res);
            }).catch(err => {
                console.log('err msg: ', err.response.data)
                alert(err.response.data)
            })
            this.commitChanges({changed:newEvent});
            this.toggleEditingFormVisibility();
        }

    }
    //
    toggleEditingFormVisibility() {
        const { editingFormVisible } = this.state;
        this.setState({
            editingFormVisible: !editingFormVisible,
        });
    }

    render() {
        const { data,
            currentDate,
            allowDeleting,
            resources,
            grouping,
            addedAppointment,
            appointmentChanges,
            editingAppointment,
            editingFormVisible } = this.state;

        const CommandButton = ({ id, ...restProps }) => {
            if (id === 'deleteButton') {
                return <AppointmentForm.CommandButton id={id} {...restProps} disabled={!allowDeleting} onExecute={this.deleteEvent} />;
            }
            if (id === 'saveButton') {
                //this.toggleEditingFormVisibility(editingFormVisible);
                return <AppointmentForm.CommandButton id={id} {...restProps} onExecute={this.saveOrUpdateEvent} />;
            }
            return <AppointmentForm.CommandButton id={id} {...restProps} />;
        };

        return (
            <Paper>
                <Scheduler
                    data={data}
                    height={660}
                >
                    <ViewState
                        currentDate={currentDate}
                        onCurrentDateChange={this.currentDateChange}
                    />
                    <EditingState
                        onCommitChanges={this.commitChanges}

                        addedAppointment={addedAppointment}
                        onAddedAppointmentChange={this.changeAddedAppointment}

                        appointmentChanges={appointmentChanges}
                        onAppointmentChangesChange={this.changeAppointmentChanges}

                        editingAppointment={editingAppointment}
                        onEditingAppointmentChange={this.changeEditingAppointment}
                    />
                    <GroupingState
                        grouping={grouping}
                    />
                    <WeekView
                        startDayHour={0}
                        endDayHour={24}
                    />
                    <Toolbar />
                    <DateNavigator />
                    <TodayButton />
                    <Appointments
                        appointmentContentComponent={appointmentContent}
                    />
                    <Resources
                        data={resources}
                        mainResourceName="roomId"
                    />
                    <IntegratedGrouping />
                    <IntegratedEditing />
                    <AppointmentTooltip
                        showCloseButton
                        showOpenButton
                        contentComponent={Content}
                    />
                    <AppointmentForm
                        commandButtonComponent={CommandButton}
                        basicLayoutComponent={BasicLayout}
                        textEditorComponent={TextEditor}
                        booleanEditorComponent={BooleanEditor}
                        visible={editingFormVisible}
                        onVisibilityChange={this.toggleEditingFormVisibility}
                    />
                    <GroupingPanel />
                </Scheduler>
            </Paper>
        );
    }
}

export default App4;