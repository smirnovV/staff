--changeset author: SmirnovV
create sequence hibernate_sequence;

CREATE TABLE organization
(
    id                bigint       NOT NULL UNIQUE,
    "Name"            varchar(100) NOT NULL,
    head_organization bigint,
    CONSTRAINT organization_pk PRIMARY KEY (id)
);

COMMENT ON COLUMN organization.id IS 'УИД';
COMMENT ON COLUMN organization."Name" IS 'Наименование';
COMMENT ON COLUMN organization.head_organization IS 'УИД головного офиса';

ALTER TABLE organization
    ADD CONSTRAINT "head_organization_FKey" FOREIGN KEY (head_organization)
        REFERENCES organization (id) MATCH FULL
        ON DELETE NO ACTION ON UPDATE NO ACTION;

--changeset author: SmirnovV
CREATE TABLE staff
(
    id              bigint       NOT NULL UNIQUE,
    name            varchar(100) NOT NULL,
    organization_id bigint       NOT NULL,
    boss            bigint       NOT NULL,
    CONSTRAINT staff_pk PRIMARY KEY (id)

);

COMMENT ON TABLE staff IS 'Сведения об организации';
COMMENT ON COLUMN staff.id IS 'УИД';
COMMENT ON COLUMN staff.name IS 'Имя';
COMMENT ON COLUMN staff.organization_id IS 'УИД организации';
COMMENT ON COLUMN staff.boss IS 'Руководитель';

ALTER TABLE staff
    ADD CONSTRAINT "staff_boss_FKey" FOREIGN KEY (boss)
        REFERENCES staff (id) MATCH FULL
        ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE staff
    ADD CONSTRAINT "staff_organization_FKey" FOREIGN KEY (organization_id)
        REFERENCES organization (id) MATCH FULL
        ON DELETE CASCADE ON UPDATE NO ACTION;