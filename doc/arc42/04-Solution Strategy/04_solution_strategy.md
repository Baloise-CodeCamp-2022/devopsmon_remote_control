# 04 Solution Strategy

## Design Goals
The following major goals were followed with the solution of DevOpsMon:

* KISS - keep it simple stupid!
* Do not rely on centralized components!
* Stay evolutionary and enable future enhancements!
* Apply to a global target audience (i.e. Group IT) - and not just for one SBU.
* The solution should be combinable with existing solutions used for measuring KPI, i.e.
  * micrometer
  * Prometheus Alerting
  * Dynatrace Alerting
  * Splunk Alerting

## Event
First core entity of this solution is the "Event":
* It is used to cover observations either by components themselves or "watchdogs"
* It should be as flexible as possible not standardizing too much and thus causing too big dependencies.

This leads to the following structure of events:

| Identity                                                                                                   ||
|------------------------------------------------------------------------------------------------------------|---|
| UUID| unique ID across all systems used for event identification|
| Trace-ID/ CID    | ID used to provide event corelations                                                                                                               |
| Affected System  | system that is affected by the event                                                                                  |
| Reporting System | either empty or system that is reporting the event (i.e. in case of a monitor/ watchdog)                              |
| Specific | specific data describing that event, not defined in detail.                                                           |

We did not take into the account following attributes:
* _type_: a classification of an event is part of the interpretation and must not be done while creating the event.

| Situation  ||
|------------|---|
| Name       | descriptive name used for event identification, must not be unique but helps toe create and apply rules|
| Initiator  | descriptive and/or identifying name (i.e. app ID, B-Key) of a person or system who initiated the event|
| created At | timestamp when the event was created, can be used for sorting and sequencing|
| Tags| collection of tags/ labels used to give a more precise description|

We did not take into the account following attributes:
* _category_: as with "type" we think categories must be part of the rules engine and not the event observation.
* _priority/ severity_: same as with category or type these two dimensions are part of proper interpretation and thus not elements of a core event. 
* _Operating System/ JDK/..._: this moe or less static info might be useful sometimes and could then be part of specific - however i.e. in cases of business events they are irrelevant.
* _Business Process ID_: same as with OS etc, we sometimes might have one, often not.
* _location/ instance_: will be described in a separate section

|System||
|---|---|
|Instance ID| URL or unique identifier for an instance that makes it accesible/ conzrollable/ etc.|

We did not take into the account following attributes:
* _location/ ID/ name_: should already be covered by the instance ID

